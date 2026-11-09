#!/usr/bin/env bash
#
# Create a permanent Google Play *upload* keystore for MBBS Handbook.
#
# Run this ONCE, on a trusted machine, and keep the resulting files safe forever:
# the same key must sign every future update (or Play Console must be told about
# a new upload key). The generated files are gitignored.
#
#   ./scripts/generate_signing_key.sh
#
# Environment overrides:
#   KEYSTORE_ALIAS   key alias             (default: mbbshandbook)
#   KEYSTORE_DNAME   certificate subject  (default: see below)
#   KEYSTORE_OUT     output path           (default: release-keystore.jks)
#   KEYSTORE_PASSWORD / KEY_PASSWORD       (default: freshly generated)
#
set -euo pipefail

ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
cd "$ROOT"

ALIAS="${KEYSTORE_ALIAS:-mbbshandbook}"
OUT="${KEYSTORE_OUT:-release-keystore.jks}"
DNAME="${KEYSTORE_DNAME:-CN=MBBS Handbook, OU=Android Release, O=MBBS Handbook, L=Abuja, ST=FCT, C=NG}"

if ! command -v keytool >/dev/null 2>&1; then
  echo "error: keytool not found. Install a JDK 17+ (e.g. 'sudo apt install openjdk-17-jdk')." >&2
  exit 1
fi

if [ -f "$OUT" ] && [ "${FORCE:-false}" != "true" ]; then
  echo "error: $OUT already exists. Refusing to overwrite your upload key." >&2
  echo "       Set FORCE=true only if you really want to destroy it." >&2
  exit 1
fi

gen_password() { openssl rand -base64 48 | tr -dc 'A-Za-z0-9' | head -c 32; }

STORE_PASSWORD="${KEYSTORE_PASSWORD:-$(gen_password)}"
KEY_PASSWORD="${KEY_PASSWORD:-$(gen_password)}"

keytool -genkeypair -v \
  -keystore "$OUT" -storetype JKS -alias "$ALIAS" \
  -keyalg RSA -keysize 4096 -validity 10950 -sigalg SHA256withRSA \
  -storepass "$STORE_PASSWORD" -keypass "$KEY_PASSWORD" \
  -dname "$DNAME"

cat > keystore.properties <<EOF
storeFile=$OUT
storePassword=$STORE_PASSWORD
keyAlias=$ALIAS
keyPassword=$KEY_PASSWORD
storeType=JKS
EOF
chmod 600 keystore.properties "$OUT"

mkdir -p .signing
base64 -w0 "$OUT" > .signing/release-keystore.jks.base64 2>/dev/null \
  || base64 -i "$OUT" | tr -d '\n' > .signing/release-keystore.jks.base64
cat > .signing/secrets.env <<EOF
KEYSTORE_PASSWORD=$STORE_PASSWORD
KEY_ALIAS=$ALIAS
KEY_PASSWORD=$KEY_PASSWORD
EOF
chmod 600 .signing/secrets.env .signing/release-keystore.jks.base64

cat <<EOF

Upload keystore created: $OUT (alias: $ALIAS)

  BACK THIS UP NOW — without it you cannot ship an update of an existing app:
    $OUT
    keystore.properties
    .signing/secrets.env

  Password is also printed below once; store it in your password manager.

  store password : $STORE_PASSWORD
  key password   : $KEY_PASSWORD

Next: publish the secrets to GitHub so CI signs release builds.

  ./scripts/set_signing_secrets.sh
EOF
