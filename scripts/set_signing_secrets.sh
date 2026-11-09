#!/usr/bin/env bash
#
# Upload the release signing material (and optional Play publishing key) to
# GitHub Actions secrets for this repository.
#
#   ./scripts/set_signing_secrets.sh
#
# Requires: gh CLI, authenticated as a user with admin rights on the repo
#           (`gh auth login`), plus the files created by
#           ./scripts/generate_signing_key.sh, or ETV env vars below.
#
# Reads (in this order):
#   .signing/secrets.env      KEYSTORE_PASSWORD, KEY_ALIAS, KEY_PASSWORD
#   .signing/release-keystore.jks.base64  (or release-keystore.jks -> base64)
#   keystore.properties
#
# Optional secret:
#   PLAY_SERVICE_ACCOUNT_JSON  path to the Play Console service-account JSON
#                              (env var, or pass --play-key <file>). Needed only
#                              for automated uploads to a Play track.
#
# Secrets written:
#   KEYSTORE_BASE64, KEYSTORE_PASSWORD, KEY_ALIAS, KEY_PASSWORD
#   PLAY_SERVICE_ACCOUNT_JSON (only when a service-account file is supplied)
#
set -euo pipefail

ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
cd "$ROOT"

REPO="${GH_REPO:-$(gh repo view --json nameWithOwner -q .nameWithOwner 2>/dev/null || true)}"
PLAY_KEY_FILE="${PLAY_SERVICE_ACCOUNT_JSON:-}"

while [ $# -gt 0 ]; do
  case "$1" in
    --repo) REPO="$2"; shift 2 ;;
    --play-key) PLAY_KEY_FILE="$2"; shift 2 ;;
    -h|--help) sed -n '2,30p' "$0"; exit 0 ;;
    *) echo "unknown argument: $1" >&2; exit 1 ;;
  esac
done

if [ -z "$REPO" ]; then
  echo "error: could not detect the GitHub repo; pass --repo owner/name" >&2
  exit 1
fi

command -v gh >/dev/null || { echo "error: gh CLI is required" >&2; exit 1; }

printable_prop() { # key from keystore.properties
  [ -f keystore.properties ] || return 1
  sed -n "s/^$1=//p" keystore.properties | head -1
}

if [ -f .signing/secrets.env ]; then
  # shellcheck disable=SC1091
  . .signing/secrets.env
fi
KEYSTORE_PASSWORD="${KEYSTORE_PASSWORD:-$(printable_prop storePassword || true)}"
KEY_ALIAS="${KEY_ALIAS:-$(printable_prop keyAlias || true)}"
KEY_PASSWORD="${KEY_PASSWORD:-$(printable_prop keyPassword || true)}"

if [ -f .signing/release-keystore.jks.base64 ]; then
  KEYSTORE_B64="$(tr -d '\n' < .signing/release-keystore.jks.base64)"
elif [ -f release-keystore.jks ]; then
  KEYSTORE_B64="$(base64 -w0 release-keystore.jks 2>/dev/null || base64 -i release-keystore.jks | tr -d '\n')"
else
  echo "error: no keystore found. Run ./scripts/generate_signing_key.sh first." >&2
  exit 1
fi

missing=0
for var in KEYSTORE_PASSWORD KEY_ALIAS KEY_PASSWORD; do
  if [ -z "${!var:-}" ]; then
    echo "error: $var is empty (check .signing/secrets.env or keystore.properties)" >&2
    missing=1
  fi
done
[ "$missing" -eq 0 ] || exit 1

echo "Publishing signing secrets to $REPO …"
printf '%s' "$KEYSTORE_B64" | gh secret set KEYSTORE_BASE64 --repo "$REPO"
gh secret set KEYSTORE_PASSWORD --repo "$REPO" --body "$KEYSTORE_PASSWORD"
gh secret set KEY_ALIAS --repo "$REPO" --body "$KEY_ALIAS"
gh secret set KEY_PASSWORD --repo "$REPO" --body "$KEY_PASSWORD"

if [ -n "$PLAY_KEY_FILE" ]; then
  if [ ! -f "$PLAY_KEY_FILE" ]; then
    echo "error: Play service-account JSON not found: $PLAY_KEY_FILE" >&2
    exit 1
  fi
  echo "Publishing PLAY_SERVICE_ACCOUNT_JSON …"
  gh secret set PLAY_SERVICE_ACCOUNT_JSON --repo "$REPO" < "$PLAY_KEY_FILE"
fi

echo
gh secret list --repo "$REPO" || {
  echo "warning: could not list secrets (the token may lack 'Secrets: read' permission)."
}
echo
echo "Done. The next CI run on this repo will sign release APK/AAB with the upload key."
echo "Run './gradlew verifyReleaseSigning bundleRelease' locally to confirm before uploading."
