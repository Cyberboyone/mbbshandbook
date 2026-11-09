# Google Play readiness — MBBS Handbook

**Status: not ready for a production upload yet.** The codebase now clears the
API-level blocker and uses the publisher-supplied AdMob IDs, but several Play
Console and release-engineering steps remain.

## Done in this branch

- [x] Launcher icon updated to book + stethoscope (adaptive, round, legacy PNGs,
      monochrome/themed, 512px store icon).
- [x] `compileSdk` / `targetSdk` raised to **36** (Android 16). New apps and
      updates must target API 36 from 31 Aug 2026.
- [x] AdMob integrated with the **publisher-supplied app, banner and Native
      Advanced IDs**. Existing banner placements are unchanged (bottom plus
      every four data rows). One additional native card follows row six only in
      eligible browse/catalog lists with more content below; active study/detail
      flows do not receive native ads.
- [x] UMP consent gate before ad initialization; privacy-options button appears
      when required; unit tests guard the configured IDs.
- [x] README no longer claims "no internet / no tracking".

## Required before uploading a release

1. **Release signing / Play App Signing**
   - Google Play rejects debug-signed artifacts. Older CI AABs were debug-signed;
     do not upload them. Release builds now require a real upload key and fail
     explicitly when signing secrets are absent; no new release artifacts are
     uploaded from that failed build.
   - Reuse the existing upload key if this app is already enrolled. For a new app,
     create a permanent upload keystore, add `KEYSTORE_BASE64`,
     `KEYSTORE_PASSWORD`, `KEY_ALIAS`, `KEY_PASSWORD` repository secrets, and
     enroll in Play App Signing. Keep the upload key backed up securely.
   - Re-run Android Build after adding secrets; upload only the newly generated
     `mbbshandbook-release-aab` after `verifyReleaseSigning` passes.
   - Enter credentials only in GitHub Secrets/local ignored files, never in chat
     or committed files. For a new app, `python3 scripts/setup_release_signing.py`
     generates and uploads them without printing secrets; back up its private
     recovery directory. See README → Release signing for the exact steps.
2. **Verify the live AdMob setup**
   - App ID `ca-app-pub-9529770421530115~3987689260`, banner unit
     `ca-app-pub-9529770421530115/5109199243`, and Native Advanced unit
     `ca-app-pub-9529770421530115/8467833630` are configured. Confirm all belong
     to this app in AdMob and that the app/store association is complete.
   - Verify native image/video fill on a registered test device. Check the
     “Advertisement” label, unobscured AdChoices, asset clicks, muted video,
     background pause, navigation cleanup, and no placeholder on no-fill.
   - Declare **Contains ads = Yes** in Play Console App content.
   - Use registered AdMob test devices while validating; do not click your own
     live ads. New units may not fill immediately.
3. **Privacy policy**
   - Host a public HTTPS privacy policy (not a PDF/login-walled page). Add the URL
     to the Play listing and link it in-app.
   - Disclose Google Mobile Ads/UMP data practices: advertising ID, app set ID,
     IP-derived coarse location, device/app diagnostics, ad interactions, and
     consent choices. See `docs/privacy-policy-template.md` as a starting draft.
4. **Play Data Safety form**
   - Complete it consistently with the privacy policy and the AdMob SDK's data
     disclosure. Include third-party SDK collection/sharing, encryption in
     transit, and data deletion/contact instructions.
5. **UMP / consent configuration**
   - In AdMob → Privacy & messaging, create the required GDPR/EEA-UK-Switzerland
     message and any US state/CPRA message for this publisher app ID. Publish
     them before release. Test with UMP debug geography on a
     registered test device before release. The app itself does not enable debug
     geography or reset consent automatically.
6. **Store listing assets**
   - 512×512 icon (provided: `store-listing/icon-512.png`), 1024×500 feature
     graphic, phone screenshots (and tablet if targeting tablets), short/full
     description, category, contact email, privacy policy URL.
7. **App content declarations**
   - Content rating questionnaire (medical education, no user-generated content).
   - Target audience: medical students/adults; do not target children.
   - Ads declaration, Data Safety, privacy policy, and any health/medical
     disclaimer. Keep the in-app educational disclaimer prominent.
8. **Closed testing (new personal developer accounts)**
   - Personal accounts created after 13 Nov 2023 must run a closed test with at
     least **12 testers opted in continuously for 14 days**, then apply for
     production access. Organization accounts are exempt.
9. **Pre-launch / device testing**
   - Run Play pre-launch report, test on Android 7/10/13/15/16, dark mode,
     edge-to-edge, offline mode, consent granted/denied/revoked, banner/native
     load failure and retry, native navigation/late callbacks, and upgrade from
     the previous release (signature must match).
10. **Versioning**
   - This branch bumps to `versionCode 8` / `versionName 1.5.0`. Increase
     `versionCode` for every Play upload.

## Quick release command path

```bash
# locally: after ignored keystore.properties is configured
./gradlew testDebugUnitTest verifyReleaseSigning bundleRelease
# upload app/build/outputs/bundle/release/app-release.aab to Play Console
```

## Sources

- Play target API requirement: https://support.google.com/googleplay/android-developer/answer/11926878
- AdMob Android quick start / test ads: https://developers.google.com/admob/android/quick-start
- UMP consent: https://developers.google.com/admob/android/privacy
- Native loading / rendering: https://developers.google.com/admob/android/native
  and https://developers.google.com/admob/android/native/advanced
- Android app signing: https://developer.android.com/studio/publish/app-signing
- Play app review / ads / privacy policy: https://support.google.com/googleplay/android-developer/answer/9859455
- Personal-account closed testing: https://support.google.com/googleplay/android-developer/answer/14151465
