#!/usr/bin/env bash
#
# Verify that the built release APK/AAB were signed with the *upload* key and not
# with the Android debug key (Google Play rejects debug-signed uploads).
#
#   ./scripts/verify-release-signing.sh [app-release.aab] [app-release.apk]
#
# Needs keytool (JDK) and, for the APK, apksigner from the Android build-tools
# ($ANDROID_HOME). Both artifacts are checked when present.
#
set -euo pipefail

ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
cd "$ROOT"

AAB="${1:-app/build/outputs/bundle/release/app-release.aab}"
APK="${2:-app/build/outputs/apk/release/app-release.apk}"

prop() { [ -f keystore.properties ] && sed -n "s/^$1=//p" keystore.properties | head -1 || true; }

KEYSTORE_FILE="${KEYSTORE_FILE:-$(prop storeFile)}"
KEYSTORE_FILE="${KEYSTORE_FILE:-release-keystore.jks}"
KEYSTORE_PASSWORD="${KEYSTORE_PASSWORD:-$(prop storePassword)}"
KEY_ALIAS="${KEY_ALIAS:-$(prop keyAlias)}"
KEYTOOL="${KEYTOOL:-keytool}"

[ -f "$KEYSTORE_FILE" ] || { echo "error: keystore not found: $KEYSTORE_FILE" >&2; exit 1; }
[ -n "$KEYSTORE_PASSWORD" ] || { echo "error: keystore password not set" >&2; exit 1; }
[ -n "$KEY_ALIAS" ] || { echo "error: key alias not set" >&2; exit 1; }
command -v "$KEYTOOL" >/dev/null || { echo "error: keytool not found" >&2; exit 1; }

normalize() { tr 'A-F' 'a-f' | tr -d ':' | tr -d ' ' | tr -d '\n'; }

EXPECTED="$("$KEYTOOL" -list -v -keystore "$KEYSTORE_FILE" -storepass "$KEYSTORE_PASSWORD" \
  -alias "$KEY_ALIAS" 2>/dev/null | awk '/SHA256:/{print $2; exit}' | normalize)"

[ -n "$EXPECTED" ] || { echo "error: could not read the certificate from $KEYSTORE_FILE" >&2; exit 1; }
echo "upload key SHA-256 : $EXPECTED"

find_apksigner() {
  command -v apksigner 2>/dev/null && return 0
  for dir in "${ANDROID_HOME:-}" "${ANDROID_SDK_ROOT:-}" "$HOME/Android/Sdk"; do
    [ -n "$dir" ] || continue
    found="$(find "$dir/build-tools" -maxdepth 2 -name apksigner -type f 2>/dev/null | sort -V | tail -1)"
    [ -n "$found" ] && { echo "$found"; return 0; }
  done
  return 1
}

cert_of() { # $1 = artifact, $2 = apk|aab
  local artifact="$1" kind="$2" out
  if [ "$kind" = "apk" ]; then
    local apksigner
    if apksigner="$(find_apksigner)"; then
      out="$("$apksigner" verify --print-certs "$artifact" 2>/dev/null \
        | awk -F': ' '/SHA-256 digest/{print $2; exit}')"
      [ -n "$out" ] && { printf '%s' "$out" | normalize; return 0; }
    fi
  fi
  "$KEYTOOL" -printcert -jarfile "$artifact" 2>/dev/null \
    | awk '/SHA256:/{print $2; exit}' | normalize
}

checked=0
failed=0
for spec in "$AAB:aab" "$APK:apk"; do
  artifact="${spec%:*}"
  kind="${spec##*:}"
  [ -f "$artifact" ] || { echo "· skip $artifact (not built)"; continue; }

  actual="$(cert_of "$artifact" "$kind" || true)"
  if [ -z "$actual" ]; then
    echo "✗ $artifact: could not read the signing certificate"
    failed=1
    continue
  fi
  if [ "$actual" = "$EXPECTED" ]; then
    echo "✓ $artifact: signed with the upload key"
  else
    echo "✗ $artifact: signed with a DIFFERENT key (got $actual) — often the debug key."
    echo "  Google Play only accepts uploads signed with the upload key."
    failed=1
  fi
  checked=$((checked + 1))
done

if [ "$checked" -eq 0 ]; then
  echo "error: no artifacts found. Build them first: ./gradlew bundleRelease assembleRelease" >&2
  exit 1
fi

[ "$failed" -eq 0 ] || { echo; echo "Signing verification FAILED." >&2; exit 1; }
echo
echo "Signing verification passed: every artifact uses the configured upload key."
