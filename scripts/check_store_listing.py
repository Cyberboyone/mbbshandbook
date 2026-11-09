#!/usr/bin/env python3
"""Validate Play Store listing metadata and assets before an upload.

Checks the fastlane metadata tree (text limits), the required graphics, the
privacy policy page and the in-app/manifest privacy URL wiring. Exits non-zero
on any problem so CI can gate a release.

Usage: python3 scripts/check_store_listing.py
Pillow is optional; image dimension checks are skipped without it.
"""

from __future__ import annotations

import re
import sys
from pathlib import Path

ROOT = Path(__file__).resolve().parent.parent
META = ROOT / "fastlane" / "metadata" / "android" / "en-US"
STORE = ROOT / "store-listing"

errors: list[str] = []
notes: list[str] = []

# Play Console hard limits.
LIMITS = {
    "title.txt": 30,
    "short_description.txt": 80,
    "full_description.txt": 4000,
}
PLACEHOLDERS = ("[DATE]", "[SUPPORT EMAIL]", "TODO", "TBD", "lorem ipsum", "example.com")


def check_text(name: str, limit: int) -> None:
    path = META / name
    if not path.exists():
        errors.append(f"missing {path.relative_to(ROOT)}")
        return
    body = path.read_text(encoding="utf-8").strip()
    if not body:
        errors.append(f"{name} is empty")
        return
    if len(body) > limit:
        errors.append(f"{name} is {len(body)} characters (Play limit {limit})")
    else:
        notes.append(f"{name}: {len(body)}/{limit} characters")
    lowered = body.lower()
    for token in PLACEHOLDERS:
        if token.lower() in lowered:
            errors.append(f"{name} still contains the placeholder {token!r}")


def check_changelog(version_code: int) -> None:
    path = META / "changelogs" / f"{version_code}.txt"
    if not path.exists():
        errors.append(
            f"missing changelog for versionCode {version_code}: "
            f"{path.relative_to(ROOT)} (max 500 characters)"
        )
        return
    body = path.read_text(encoding="utf-8").strip()
    if len(body) > 500:
        errors.append(f"changelogs/{version_code}.txt is {len(body)} characters (limit 500)")
    elif not body:
        errors.append(f"changelogs/{version_code}.txt is empty")
    else:
        notes.append(f"changelogs/{version_code}.txt: {len(body)}/500 characters")


def gradle_version() -> tuple[int, str]:
    text = (ROOT / "app" / "build.gradle.kts").read_text(encoding="utf-8")
    code = re.search(r"versionCode\s*=\s*(\d+)", text)
    name = re.search(r'versionName\s*=\s*"([^"]+)"', text)
    if not code or not name:
        errors.append("could not read versionCode/versionName from app/build.gradle.kts")
        return 0, "0.0.0"
    return int(code.group(1)), name.group(1)


def check_image(path: Path, width: int, height: int, max_bytes: int, allow_alpha: bool = False) -> None:
    if not path.exists():
        errors.append(f"missing {path.relative_to(ROOT)}")
        return
    size = path.stat().st_size
    if size > max_bytes:
        errors.append(f"{path.name} is {size / 1_048_576:.1f} MB (Play limit {max_bytes // 1_048_576} MB)")
    try:
        from PIL import Image  # noqa: PLC0415
    except ImportError:
        notes.append(f"{path.name}: dimensions not checked (Pillow not installed)")
        return
    with Image.open(path) as image:
        if image.size != (width, height):
            errors.append(f"{path.name} is {image.size[0]}x{image.size[1]} (Play requires {width}x{height})")
        else:
            notes.append(f"{path.name}: {width}x{height}")
        if image.mode not in (("RGB", "RGBA") if allow_alpha else ("RGB",)):
            errors.append(
                f"{path.name} uses mode {image.mode}; Play needs "
                + ("RGB or RGBA" if allow_alpha else "an opaque RGB image with no transparency")
            )


def check_privacy_policy() -> None:
    html = ROOT / "docs" / "privacy-policy.html"
    md = ROOT / "docs" / "privacy-policy.md"
    for path in (html, md):
        if not path.exists():
            errors.append(f"missing {path.relative_to(ROOT)}")
            continue
        body = path.read_text(encoding="utf-8")
        for token in PLACEHOLDERS:
            if token.lower() in body.lower():
                errors.append(f"{path.name} still contains the placeholder {token!r}")
        if "https://" not in body:
            errors.append(f"{path.name} has no public HTTPS URL reference")

    legal = ROOT / "app" / "src" / "main" / "java" / "com" / "mbbshandbook" / "app" / "Legal.kt"
    manifest = ROOT / "app" / "src" / "main" / "AndroidManifest.xml"
    if legal.exists() and html.exists():
        url = re.search(r'PRIVACY_POLICY_URL\s*=\s*\n?\s*"([^"]+)"', legal.read_text(encoding="utf-8"))
        if not url:
            errors.append("could not read Legal.PRIVACY_POLICY_URL")
        else:
            value = url.group(1)
            if not value.startswith("https://"):
                errors.append("Legal.PRIVACY_POLICY_URL must be HTTPS")
            if manifest.exists() and value not in manifest.read_text(encoding="utf-8"):
                errors.append("AndroidManifest.xml advertises a different privacy policy URL than Legal.kt")
            expected_path = value.split("/")[-1]
            if not (ROOT / "docs" / expected_path).exists():
                errors.append(f"privacy policy URL points at docs/{expected_path}, which does not exist")
            notes.append(f"privacy policy: {value}")


def main() -> int:
    code, name = gradle_version()
    notes.append(f"versionCode {code} / versionName {name}")

    for file, limit in LIMITS.items():
        check_text(file, limit)
    check_changelog(code)

    check_image(STORE / "icon-512.png", 512, 512, 1_048_576, allow_alpha=True)
    check_image(STORE / "feature-graphic-1024x500.png", 1024, 500, 15 * 1_048_576)
    check_privacy_policy()

    if (META / "images" / "icon.png").exists():
        notes.append("fastlane icon.png present")
    else:
        errors.append("missing fastlane/metadata/android/en-US/images/icon.png")

    screenshots = sorted((META / "images" / "phoneScreenshots").glob("*.png"))
    if not screenshots:
        notes.append(
            "phoneScreenshots/ is empty — Play needs at least 2 (see store-listing/README.md)"
        )
    else:
        notes.append(f"phone screenshots: {len(screenshots)}")

    print("Play listing check")
    for note in notes:
        print(f"  · {note}")
    if errors:
        print("\nProblems:")
        for problem in errors:
            print(f"  ✗ {problem}")
        return 1
    print("\nAll checked listing requirements passed.")
    return 0


if __name__ == "__main__":
    sys.exit(main())
