# Release engineering — signing, secrets and uploads

Everything needed to turn this repository into an uploadable Google Play artifact.

```
keystore ──► GitHub secrets ──► CI signs APK + AAB ──► Play Console (or Play Release workflow)
```

## 1. One-time setup

### 1.1 Create the upload keystore

Run on a trusted machine with a JDK installed:

```bash
./scripts/generate_signing_key.sh
```

This writes (all gitignored):

| File | Purpose |
|---|---|
| `release-keystore.jks` | the permanent upload key |
| `keystore.properties` | local Gradle signing config (never commit) |
| `.signing/secrets.env` | passwords/alias in `KEY=VALUE` form |
| `.signing/release-keystore.jks.base64` | base64 copy uploaded to CI |

> ⚠️ **Back this up before you upload anything.** If the upload key is lost and
> Play App Signing is not enrolled, you cannot publish an update to an existing
> listing — you would have to publish a brand-new app. Keep two offline copies
> plus your password manager. Losing the password is the same as losing the key.

### 1.2 Publish the secrets to GitHub

```bash
./scripts/set_signing_secrets.sh            # signing secrets only
./scripts/set_signing_secrets.sh --play-key ~/play-service-account.json
```

The script uses the `gh` CLI and needs a token that can write **Actions secrets**
(`repo` scope or a fine-grained token with *Secrets: Read and write*). If the
Arena/GitHub connection cannot manage secrets you will see
`Resource not accessible by integration` — in that case run the script locally
with `gh auth login`, or paste the values in
**Settings → Secrets and variables → Actions**.

### 1.3 Secret inventory

| Secret | Required | Value | Notes |
|---|---|---|---|
| `KEYSTORE_BASE64` | ✅ | `base64 -w0 release-keystore.jks` | `.signing/release-keystore.jks.base64` |
| `KEYSTORE_PASSWORD` | ✅ | keystore password | `.signing/secrets.env` |
| `KEY_ALIAS` | ✅ | `mbbshandbook` | `.signing/secrets.env` |
| `KEY_PASSWORD` | ✅ | key password | `.signing/secrets.env` |
| `PLAY_SERVICE_ACCOUNT_JSON` | optional | Play Console service-account JSON | only for the **Play Release** workflow |

With those four signing secrets present, every CI run signs the release APK and
AAB and then verifies the signature against the upload certificate
(`scripts/verify-release-signing.sh`). If the check fails the build fails.

Independently of CI, `./gradlew verifyReleaseSigning` fails when no upload key is
configured, and `REQUIRE_RELEASE_SIGNING=true` (or
`-PrequireReleaseSigning=true`) makes any Gradle invocation fail without one, so a
debug-signed bundle cannot be produced silently. After the build,
`scripts/verify-release-signing.sh` compares the artifact's certificate with the
keystore — run it before every upload (CI does this in the workflow patch).

### 1.4 Enrol in Play App Signing

Play Console → your app → **Release → Setup → App signing**. Google keeps the
app-signing key; your `release-keystore.jks` becomes the *upload key*. If the
upload key is ever compromised or lost, you can request a reset — this is the
main reason to enrol.

### 1.5 Service account for automated uploads (optional)

1. Play Console → **Settings → API access** → link a Google Cloud project.
2. Create a service account, grant **Release to testing tracks** (and
   **Release to production** when you are ready).
3. Create a JSON key and add it as `PLAY_SERVICE_ACCOUNT_JSON`.

## 2. Every release

1. Bump `versionCode` (and usually `versionName`) in
   `app/build.gradle.kts`. Play rejects a repeated `versionCode`.
2. Add `fastlane/metadata/android/en-US/changelogs/<versionCode>.txt`
   (≤ 500 characters). The listing checker fails without it.
3. Update the store description if the feature set changed.
4. Build and verify locally:

   ```bash
   ./gradlew verifyReleaseSigning bundleRelease
   ./scripts/verify-release-signing.sh app/build/outputs/bundle/release/app-release.aab
   ```
5. Validate listing assets:

   ```bash
   python3 scripts/check_store_listing.py     # needs: pip install pillow
   ```
6. Upload one of two ways:
   - **Manual:** Play Console → Production/Testing → Create release → upload
     `app/build/outputs/bundle/release/app-release.aab`.
   - **Automated:** GitHub → Actions → **Play Release** → Run workflow, choosing
     track and status. `fastlane/metadata/android/en-US/` supplies the listing,
     graphics and changelog.

Tagging `v1.6.0` also attaches the built APKs/AAB and `SHA256SUMS.txt` to a
GitHub Release.

## 3. Local builds without secrets

Without `keystore.properties` (or the env vars) release builds fall back to the
debug key so contributors can still test. `verifyReleaseSigning` and any `v*`
tag build fail instead, so a debug-signed bundle can never reach Play by accident.

Environment variables Gradle understands:

```bash
KEYSTORE_FILE=release-keystore.jks \
KEYSTORE_PASSWORD=… KEY_ALIAS=mbbshandbook KEY_PASSWORD=… \
./gradlew bundleRelease
```

## 4. Rotating or recovering keys

- **Upload key lost:** Play Console → App signing → *Request upload key reset*.
  Generate a new keystore with `FORCE=true ./scripts/generate_signing_key.sh`
  and re-run `scripts/set_signing_secrets.sh`.
- **Upload key leaked:** reset the upload key immediately (same path) and keep
  the app-signing key with Google untouched.
- **Passwords changed:** re-run `scripts/set_signing_secrets.sh`; nothing in the
  repository changes because no secret is ever committed.

## 5. Troubleshooting

| Symptom | Cause | Fix |
|---|---|---|
| `Signing verification FAILED` in CI | secrets missing/incorrect | re-run `scripts/set_signing_secrets.sh` |
| Play: "You uploaded an APK/AAB that was signed with the wrong key" | keystore does not match the enrolled upload key | use the original keystore or reset the upload key |
| Play: "Version code N has already been used" | versionCode not bumped | bump it in `app/build.gradle.kts` |
| Play Release workflow: `PLAY_SERVICE_ACCOUNT_JSON is not set` | secret missing | add it (section 1.5) |
| Build fails with "Release signing is required" | `REQUIRE_RELEASE_SIGNING=true` without secrets | add secrets or unset the flag |
