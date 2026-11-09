# Google Play readiness — MBBS Handbook

**Status: everything in the repository is upload-ready. What remains are
account-level actions that only the Play/GitHub account owner can perform**
(setting GitHub secrets, enabling GitHub Pages, filling Play Console forms, and
pressing *Upload*).

Version: `versionCode 9` / `versionName 1.6.0` · target API 36 · min API 24 ·
package `com.mbbshandbook.app`.

## Repository-side readiness

| Requirement | Status | Where |
|---|---|---|
| Target/compile API 36 (Play requirement from 31 Aug 2026) | ✅ | `app/build.gradle.kts` |
| Upload-key signing config, no debug-signed Play artifacts | ✅ | `app/build.gradle.kts`, `scripts/verify-release-signing.sh` |
| Release build aborts if signing is missing on a `v*` tag | ✅ | `REQUIRE_RELEASE_SIGNING` in CI |
| Gradle fails release builds without an upload key (opt-in strict mode) | ✅ | `verifyReleaseSigning`, `REQUIRE_RELEASE_SIGNING` |
| Signature verification of APK + AAB in CI, artifact checksums, lint job, Play upload workflow | ⚠️ patch ready | [`ci/README.md`](ci/README.md) — the GitHub connection lacks the *Workflows* permission to push it |
| R8 minification + resource shrinking, non-debuggable release | ✅ | `app/build.gradle.kts` |
| Cleartext HTTP blocked, network security config | ✅ | `AndroidManifest.xml`, `res/xml/network_security_config.xml` |
| Backup rules that never restore ad-consent state | ✅ | `res/xml/backup_rules.xml`, `res/xml/data_extraction_rules.xml` |
| Advertising ID permission declared for Data Safety | ✅ | `AndroidManifest.xml` |
| UMP consent gate before ads, privacy-options entry point | ✅ | `ads/AdsController.kt`, `ads/AdPrivacyOptionsButton.kt` |
| In-app **About & privacy** screen with policy + support + disclaimer | ✅ | `ui/AboutScreen.kt`, `Legal.kt` |
| Public HTTPS privacy policy, linked from app/manifest | ✅ (page written) | `docs/privacy-policy.html` |
| Icon 512×512 and 1024×500 feature graphic | ✅ | `store-listing/` |
| Title, short and full description, changelog | ✅ | `fastlane/metadata/android/en-US/` |
| Automated listing/metadata validation | ✅ | `scripts/check_store_listing.py` |
| Automated AAB upload to a Play track | ✅ (needs a service-account secret) | `.github/workflows/play-release.yml` |
| Data safety / content rating / target audience answers prepared | ✅ | `docs/DATA_SAFETY.md`, `docs/CONTENT_RATING.md` |
| Phone screenshots | ⬜ must be captured on a device | `store-listing/README.md` |
| GitHub Actions signing secrets | ⬜ needs repo-admin token | `docs/RELEASE.md` §1.2 |
| GitHub Pages enabled for the privacy policy URL | ⬜ needs repo-admin access | step 2 below |
| Play Console forms, testing track, production access | ⬜ account owner | steps 3–8 below |

## What still needs doing (in order)

### 0. Apply the CI workflow patch
`git apply docs/ci/workflows.patch` and push it with a token that has the
**Workflows** permission (or paste the two files in the GitHub web editor).
Until then the Gradle-level checks still guard every release build — see
[`ci/README.md`](ci/README.md).

### 1. Set the signing secrets
```bash
./scripts/set_signing_secrets.sh        # needs a gh token that can write Actions secrets
```
Adds `KEYSTORE_BASE64`, `KEYSTORE_PASSWORD`, `KEY_ALIAS`, `KEY_PASSWORD`.
Details, rotation and recovery: [`RELEASE.md`](RELEASE.md).

### 2. Publish the privacy policy
GitHub → **Settings → Pages → Source: Deploy from a branch → `main` / `/docs`**.
The policy then lives at
`https://cyberboyone.github.io/mbbshandbook/privacy-policy.html` — the exact URL
already used by the app (`Legal.kt`, manifest) and the docs. Any other host works
too; update `Legal.kt`, the manifest meta-data and the Play listing together
(a unit test enforces the first two).

### 3. Create the app in Play Console
Package `com.mbbshandbook.app`, free, category *Education*, contact email,
privacy policy URL, and **App signing** enrolment (upload key = the keystore from
`docs/RELEASE.md`).

### 4. Complete App content
- **Privacy policy:** the Pages URL.
- **Ads:** Contains ads = yes.
- **Data safety:** copy [`DATA_SAFETY.md`](DATA_SAFETY.md).
- **Content rating:** copy [`CONTENT_RATING.md`](CONTENT_RATING.md).
- **Target audience:** 18+, not designed for families.
- **App access:** no login required.
- **Health apps:** educational reference only, not a medical device.

### 5. AdMob side
- Confirm app ID `ca-app-pub-9529770421530115~3987689260`, banner
  `…/5109199243` and Native Advanced `…/8467833630` belong to this app and that
  the store association is complete.
- Publish the GDPR/EEA-UK-CH and US-state UMP messages; the app only *shows*
  forms configured in AdMob.
- Test on a registered test device — never click your own live ads.

### 6. Store listing
Copy the texts from `fastlane/metadata/android/en-US/`, upload
`store-listing/icon-512.png` and `store-listing/feature-graphic-1024x500.png`,
and capture 2–8 phone screenshots per `store-listing/README.md`.

### 7. First upload + testing track
Upload the signed AAB to **Internal testing** (or run the *Play Release*
workflow), then:

- pre-launch report: fix crashes, check the device matrix;
- verify ads on API 24 / 33 / 36, consent granted/denied/revoked, ad load
  failure + retry, offline mode;
- personal developer accounts created after 13 Nov 2023 must run a closed test
  with **12 testers for 14 continuous days** before applying for production.

### 8. Production
Promote the tested release, complete the staged rollout, keep the AAB and
`SHA256SUMS.txt` from the CI run archived with the release notes.

## Release commands

```bash
# tests + signed bundle + signature verification
./gradlew testDebugUnitTest verifyReleaseSigning bundleRelease
./scripts/verify-release-signing.sh app/build/outputs/bundle/release/app-release.aab

# listing validation
python3 scripts/check_store_listing.py

# regenerate store graphics
python3 scripts/generate_store_graphics.py
```

## Sources

- Target API requirement: https://support.google.com/googleplay/android-developer/answer/11926878
- Play App Signing: https://support.google.com/googleplay/android-developer/answer/9842756
- Data safety & AdMob: https://support.google.com/admob/answer/11150250
- Content rating (IARC): https://support.google.com/googleplay/android-developer/answer/9859655
- Store listing asset specs: https://support.google.com/googleplay/android-developer/answer/9866151
- Personal-account closed testing: https://support.google.com/googleplay/android-developer/answer/14151465
- UMP consent: https://developers.google.com/admob/android/privacy
