#!/usr/bin/env python3
"""Create a NEW Play upload key and send its values to GitHub Actions secrets.

Run on your own computer, not in a shared/cloud workspace. From a checkout:
    python3 scripts/setup_release_signing.py
Or download this file and run it standalone:
    python3 setup_release_signing.py

Requires Python 3.9+, JDK 17+ (keytool), and an authenticated GitHub CLI (gh).
Private key material/passwords are never printed or written inside the repo.
Keep the generated backup directory outside Git and copy it to secure storage.
This script is for a new app; reuse an existing enrolled upload key instead.
"""

from __future__ import annotations

import argparse
import base64
import os
from pathlib import Path
import re
import secrets
import shutil
import subprocess
import sys

REPO = "Cyberboyone/mbbshandbook"
ALIAS = "mbbskey"
SECRET_NAMES = ("KEYSTORE_BASE64", "KEYSTORE_PASSWORD", "KEY_ALIAS", "KEY_PASSWORD")


def run(args: list[str], *, env: dict[str, str] | None = None,
        stdin: str | None = None, capture: bool = False) -> subprocess.CompletedProcess:
    """Never use shell=True; secret values are not command-line arguments."""
    return subprocess.run(
        args, env=env, input=stdin, text=True, check=True,
        stdout=subprocess.PIPE if capture else None,
        stderr=subprocess.PIPE if capture else None,
    )


def find_keytool(explicit: str | None) -> str:
    if explicit:
        path = Path(explicit).expanduser().resolve()
        if not path.is_file():
            raise RuntimeError(f"keytool not found: {path}")
        return str(path)
    found = shutil.which("keytool")
    if found:
        return found
    java_home = os.environ.get("JAVA_HOME")
    if java_home:
        candidate = Path(java_home) / "bin" / ("keytool.exe" if os.name == "nt" else "keytool")
        if candidate.is_file():
            return str(candidate)
    raise RuntimeError("Install JDK 17+ and put keytool on PATH, or pass --keytool PATH.")


def write_private(path: Path, data: bytes) -> None:
    """Create a new owner-only file; refuse to overwrite existing material."""
    flags = os.O_WRONLY | os.O_CREAT | os.O_EXCL
    if hasattr(os, "O_BINARY"):
        flags |= os.O_BINARY
    fd = os.open(path, flags, 0o600)
    with os.fdopen(fd, "wb") as stream:
        stream.write(data)
    os.chmod(path, 0o600)


def containing_repo(path: Path) -> Path | None:
    """Find a Git root without requiring Git to be installed (standalone use)."""
    for candidate in (path, *path.parents):
        if (candidate / ".git").exists():
            return candidate
    return None


def main() -> int:
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument("--repo", default=REPO, help="GitHub owner/repository")
    parser.add_argument("--backup-dir", type=Path,
                        default=Path.home() / "MBBSHandbook-signing-backup",
                        help="NEW private backup directory outside this repository")
    parser.add_argument("--keytool", help="Path to keytool if it is not on PATH")
    parser.add_argument("--replace-secrets", action="store_true",
                        help="Explicitly replace the four existing GitHub secrets")
    args = parser.parse_args()
    if not re.fullmatch(r"[A-Za-z0-9_.-]+/[A-Za-z0-9_.-]+", args.repo):
        raise RuntimeError("Use --repo OWNER/REPOSITORY.")

    backup = args.backup_dir.expanduser().resolve()
    for start in (Path(__file__).resolve().parent, Path.cwd().resolve()):
        repo_root = containing_repo(start)
        if repo_root and (backup == repo_root or repo_root in backup.parents):
            raise RuntimeError("Choose a backup directory OUTSIDE the Git repository.")
    if backup.exists():
        raise RuntimeError(f"Backup directory already exists; refusing to overwrite it: {backup}")

    gh = shutil.which("gh")
    if not gh:
        raise RuntimeError("Install GitHub CLI, then run: gh auth login")
    keytool = find_keytool(args.keytool)
    run([keytool, "-help"], capture=True)
    run([gh, "auth", "status", "--hostname", "github.com"], capture=True)
    run([gh, "repo", "view", args.repo, "--json", "nameWithOwner"], capture=True)

    listing = run([gh, "secret", "list", "--repo", args.repo, "--app", "actions"], capture=True)
    existing = {line.split()[0] for line in (listing.stdout or "").splitlines() if line.split()}
    conflicts = sorted(existing.intersection(SECRET_NAMES))
    if conflicts and not args.replace_secrets:
        raise RuntimeError(
            "These secrets already exist: " + ", ".join(conflicts) + ". "
            "Reuse the existing enrolled upload key instead of replacing it accidentally. "
            "Only use --replace-secrets after confirming that rotation is intended."
        )

    print(f"Repository: {args.repo}")
    print(f"Private backup directory will be: {backup}")
    print("This creates a NEW permanent upload key for a NEW app.")
    print("Passwords and key material will not be displayed or stored in Git.")
    if input("Type CREATE to continue: ").strip() != "CREATE":
        print("Cancelled. Nothing was created or uploaded.")
        return 1

    backup.mkdir(mode=0o700, parents=True, exist_ok=False)
    os.chmod(backup, 0o700)
    keystore = backup / "mbbshandbook-upload.p12"
    recovery = backup / "RECOVERY-PRIVATE.txt"
    public_cert = backup / "upload-certificate.pem"
    # PKCS12 uses one password for the store and its private-key entry.
    # The two GitHub secret values are intentionally identical.
    store_password = secrets.token_urlsafe(32)
    key_password = store_password
    env = os.environ.copy()
    env["MBBS_UPLOAD_STORE_PASSWORD"] = store_password
    env["MBBS_UPLOAD_KEY_PASSWORD"] = key_password

    # keytool reads passwords from the child environment, not argv. PKCS12 is
    # the JDK 9+ default and is explicitly selected for predictable CI loading.
    run([
        keytool, "-genkeypair", "-noprompt",
        "-keystore", str(keystore), "-storetype", "PKCS12",
        "-alias", ALIAS, "-keyalg", "RSA", "-keysize", "3072",
        "-sigalg", "SHA256withRSA", "-validity", "10000",
        "-dname", "CN=MBBS Handbook Upload, O=MBBS Handbook, C=NG",
        "-storepass:env", "MBBS_UPLOAD_STORE_PASSWORD",
        "-keypass:env", "MBBS_UPLOAD_KEY_PASSWORD",
    ], env=env)
    os.chmod(keystore, 0o600)
    run([
        keytool, "-list", "-v", "-keystore", str(keystore),
        "-storetype", "PKCS12", "-alias", ALIAS,
        "-storepass:env", "MBBS_UPLOAD_STORE_PASSWORD",
    ], env=env, capture=True)
    certificate_result = run([
        keytool, "-exportcert", "-rfc", "-keystore", str(keystore),
        "-storetype", "PKCS12", "-alias", ALIAS,
        "-storepass:env", "MBBS_UPLOAD_STORE_PASSWORD",
    ], env=env, capture=True)
    write_private(public_cert, (certificate_result.stdout or "").encode("utf-8"))

    encoded = base64.b64encode(keystore.read_bytes()).decode("ascii")
    values = {
        "KEYSTORE_BASE64": encoded,
        "KEYSTORE_PASSWORD": store_password,
        "KEY_ALIAS": ALIAS,
        "KEY_PASSWORD": key_password,
    }
    # Save recovery material outside Git before network writes. This directory
    # is private (0700 on POSIX); move it to encrypted/password-manager storage.
    write_private(recovery, (
        "MBBS Handbook upload-key recovery material — PRIVATE\n"
        f"Repository: {args.repo}\n"
        f"Keystore: {keystore.name} (PKCS12)\n"
        f"KEY_ALIAS={ALIAS}\n"
        f"KEYSTORE_PASSWORD={store_password}\n"
        f"KEY_PASSWORD={key_password}\n"
        "KEYSTORE_BASE64 is the Base64 encoding of the accompanying .p12 file.\n"
        "Back up this entire directory securely. Do not email/commit/upload it.\n"
    ).encode("utf-8"))

    uploaded: list[str] = []
    try:
        for name in SECRET_NAMES:
            # gh encrypts the value locally; stdin avoids exposing it in argv.
            run([gh, "secret", "set", name, "--repo", args.repo, "--app", "actions"],
                stdin=values[name])
            uploaded.append(name)
            print(f"Uploaded GitHub secret: {name}")
    except subprocess.CalledProcessError as error:
        print(f"GitHub upload failed after: {', '.join(uploaded) or 'none'}.", file=sys.stderr)
        print(f"Your private backup is preserved at: {backup}", file=sys.stderr)
        print("Fix GitHub access and use the recovery file to set the remaining secrets; "
              "do not generate another key for the same app.", file=sys.stderr)
        return error.returncode or 1

    print("\nAll four GitHub Actions secrets are configured.")
    print(f"Private backup: {backup}")
    print("Back up that directory securely now. GitHub cannot reveal secret values later.")
    print("Re-run Android Build and upload only the newly generated release AAB to Play.")
    print("No passwords or private key material were printed.")
    return 0


if __name__ == "__main__":
    try:
        sys.exit(main())
    except subprocess.CalledProcessError as exc:
        print(f"Command failed ({exc.returncode}): {exc.cmd[0]}. "
              "Check the tool's authentication/installation and retry.", file=sys.stderr)
        sys.exit(exc.returncode or 1)
    except (RuntimeError, OSError) as exc:
        print(f"Setup stopped: {exc}", file=sys.stderr)
        sys.exit(1)
