# MBBS Students Handbook 📱🩺

A beautiful, offline-first Android handbook for MBBS students — study strategies for
every phase of medical school, clinical skills, exam technique, ethics, wellbeing,
plus interactive study tools.

Built with **Kotlin + Jetpack Compose (Material 3)** and custom **Poppins**
typography. The UI follows a dark fintech-inspired design: near-black teal
surfaces, glowing gradient progress card, pill buttons and filter chips,
cyan-accented bottom navigation, and animated transitions. No account. Core
handbook content works offline; AdMob banners and native cards use the
publisher's live app/ad-unit IDs and need a network connection.

## ✨ Features

### 📖 Handbook — 11 sections, 67 topics
Getting started · Preclinical · Paraclinical · Clinical rotations · Clinical skills ·
Exam success · Ethics · Wellbeing · Resources & career · **Mnemonics vault** ·
**Emergency quick reference** — each section with its own glowing gradient
theme. Includes ECG/CXR/ABG interpretation, lab patterns, ward calls,
suturing, SBAR handover, breaking bad news, end-of-life care, emergency drug
doses, trauma and poisoning.

### 🩺 Specialties & Roadmaps
18 specialties across Medical, Surgical and Acute/Diagnostic groups — each with
what the work is really like, a day in the life, a visual **MBBS → internship →
residency → fellowship roadmap**, key exams (WACP/NPMCN, MRCP/MRCS, USMLE routes),
lifestyle & competitiveness notes, and "start now in med school" action steps.
Searchable, with per-specialty gradient themes.

### 🎯 Interactive study tools
- **Quizzes** — 168 MCQs across 10 categories (Anatomy, Physiology, Pharmacology,
  Micro & Path, Clinical Medicine, Ethics & Skills, **Paediatrics, OB/GYN,
  Psychiatry, Emergency Medicine**) with instant feedback, explanations,
  12-question shuffled rounds, saved best scores and a confetti celebration 🎉
- **Flashcards** — 68 cards in 6 decks (Cranial Nerves, Normal Values, Antidotes,
  Clinical Vocabulary, **Paeds Milestones, O&G Emergencies**) with a 3D flip
  animation and shuffle
- **Clinical calculators** — BMI, IV drip rate + pump rate, maintenance fluids
  (4-2-1), Glasgow Coma Scale, APGAR — all unit-tested
- **Medical glossary** — 64 searchable terms A-Z

### 🧭 Reading experience
- **Progress tracking** — topics auto-mark as read; per-section and overall
  progress shown on the home screen
- **Full-text search** across every topic
- **Bookmarks** saved on-device
- **Share** any topic as text
- **Dark fintech-style theme** + edge-to-edge design, animated navigation
- Core content works **offline** (min Android 7.0 / API 24); AdMob ads need network

> ⚠️ Educational guidance for students — not medical advice. Always follow your
> school's curriculum, your supervisors and current local clinical guidelines.

## 💚 Logo / launcher icon

The launcher icon is an open book with a stethoscope, on the app's green
gradient. Adaptive, round, legacy PNG and Android 13+ themed/monochrome variants
are all generated from the same mark. A 512×512 Play Store icon is in
[`store-listing/icon-512.png`](store-listing/icon-512.png). Regenerate PNGs with:

```bash
pip install resvg-py
python3 scripts/generate_icons.py
```

## 📢 Ads (publisher AdMob configuration)

The app integrates the Google Mobile Ads SDK with the publisher-supplied IDs:

- App ID: `ca-app-pub-9529770421530115~3987689260`
- Banner unit: `ca-app-pub-9529770421530115/5109199243`
- Native Advanced unit: `ca-app-pub-9529770421530115/8467833630`

These are live publisher IDs, not Google's sample/test IDs. Register development
devices as AdMob test devices before validating, and never click your own live
ads. New ad units can take time to become eligible to serve.

Ads appear only on browse/catalog screens (home, section lists, specialties,
quiz/flashcard/calculator/glossary catalogs, search and bookmarks). Existing
banner behavior is unchanged: the bottom banner remains where present, and an
inline banner follows every four data rows while more rows follow.

The separate Native Advanced unit adds **at most one native card per eligible
list**, after the sixth data row and only when a seventh row follows. This
offsets native cards from banner slots; headers/intro text do not count as rows.
Short lists (including the six-deck flashcard and five-calculator catalogs) get
no trailing native card. Native cards use the dark teal/mint palette, an explicit
“Advertisement” label and “Ad” badge, SDK AdChoices, and real advertiser assets.
The SDK owns asset clicks and impressions; video starts muted and pauses when
the app is backgrounded. Ads are **not** added to topic reading, specialty
details, quiz rounds, flashcard study, or calculator use.

A shared UMP consent gate runs before either format initializes/requests ads;
if consent is unavailable, ads are hidden without blocking handbook content.
Banners are attached and measured before loading. Native assets are registered
inside `NativeAdView` before binding the ad, and each native ad is destroyed on
disposal (including late load callbacks). Unfilled release slots occupy no list
space. Unit tests guard publisher IDs, consent/init sequencing, duplicate banner
requests, the unchanged four-row rule, and the separate native placement rule.

**If ads do not appear:** install the **debug APK**, connect to the internet,
and check the status row above the bottom navigation. It reports privacy check,
SDK initialization, or banner-load errors and offers **Retry**. Scroll to row six
in a list with at least seven rows for the separate **Native Advanced** status/retry
row. Filter Logcat by `HandbookAds` for the format, placement and SDK error code.
Complete any privacy form shown. Release builds keep failed slots hidden and do
not expose debug diagnostics.
Scroll down to see inline ads after rows 4, 8, …; short lists with no following
row intentionally have no trailing inline ad. A valid request does not guarantee
fill: check the AdMob app/unit setup, policy status, inventory and region.

Before public release: confirm the AdMob app/store association, publish the
required UMP privacy messages for this app ID, host a privacy policy, and complete
Play Data Safety + ads declarations. IDs are defined in `ads/AdsConfig.kt`; the
manifest app ID must match it (covered by a unit test). Confirm the native unit
is enabled and select image/video options compatible with the native card. See
[`docs/PLAYSTORE.md`](docs/PLAYSTORE.md).

## CI artifacts (GitHub Actions)

Every push runs [`.github/workflows/android-build.yml`](.github/workflows/android-build.yml),
which runs the unit tests and then builds **all three artifacts**:

| Artifact | Gradle task | Download name |
|---|---|---|
| Debug APK | `assembleDebug` | `mbbshandbook-debug-apk` |
| Release APK (upload-key signed, minified) | `assembleRelease` | `mbbshandbook-release-apk` |
| Release AAB (upload-key signed) | `bundleRelease` | `mbbshandbook-release-aab` |

Download them from the **Actions tab → latest successful run → Artifacts**.
All four signing secrets must be configured for the current combined CI build
to succeed. Pushing a tag like `v1.0.0` attaches all three files to a GitHub
Release only after the build succeeds.

## Play Store readiness

The app is **not production-upload ready yet**. It now targets API 36 and has
the publisher AdMob IDs, but a release upload key must be configured in GitHub
Secrets before CI can produce a Play-uploadable AAB. Privacy policy/Data Safety,
store listing assets, content rating, and (for new personal accounts) closed
testing also still need to be completed. Checklist: [`docs/PLAYSTORE.md`](docs/PLAYSTORE.md).

## Release signing (required for Google Play)

Google Play rejects debug-signed APKs/AABs. This project **never falls back to
debug signing for release builds**. Without an upload key, the release build
fails explicitly and CI uploads no misleading release artifacts. AABs for Play
must be signed with your private upload key; Play App Signing then manages the
final app-signing key. Debug APK builds and unit tests do not require this key.

**Already published this app or enrolled an upload key?** Reuse that existing
upload keystore and alias. A different key can cause an upload-key mismatch.
For a new app, create a permanent upload key once and keep secure offline backups.

1. Create a keystore on your own computer (skip this if you already have one).
   Use your own details and strong passwords; do not send them to chat:

   ```bash
   keytool -genkeypair -v -keystore release-keystore.jks -alias mbbskey \
     -keyalg RSA -keysize 2048 -validity 10000
   ```

2. Encode the binary keystore for the GitHub secret:

   ```bash
   # Linux
   base64 -w0 release-keystore.jks > release-keystore.base64.txt
   # macOS (instead): base64 -i release-keystore.jks -o release-keystore.base64.txt
   ```

3. In GitHub, open **Settings → Secrets and variables → Actions → New repository
   secret** and add all four values. Enter values in GitHub, not in source files
   or this conversation:

   | Secret | Value |
   |---|---|
   | `KEYSTORE_BASE64` | contents of `release-keystore.base64.txt` |
   | `KEYSTORE_PASSWORD` | keystore password |
   | `KEY_ALIAS` | the existing/new alias, e.g. `mbbskey` |
   | `KEY_PASSWORD` | private-key password |

4. Re-run **Android Build** on `main` (or push a new commit). Download
   `mbbshandbook-release-aab` from that **new** run and upload it to Play Console.
   Older CI artifacts remain debug-signed; do not reuse them. The run must pass
   `verifyReleaseSigning` and log “Release upload certificate verified”. Missing
   secrets fail the release build instead of producing a Play-ready AAB.

For local release builds, create an ignored `keystore.properties` at the repo root:

```properties
storeFile=release-keystore.jks
storePassword=YOUR_KEYSTORE_PASSWORD
keyAlias=YOUR_KEY_ALIAS
keyPassword=YOUR_KEY_PASSWORD
```

Then run `./gradlew verifyReleaseSigning bundleRelease`. The guard checks the
keystore, private-key alias and certificate validity, and rejects the Android
debug certificate. The release APK and AAB are signed with the same configured
upload key. You can additionally check a generated APK with Android SDK
`apksigner verify --verbose --print-certs PATH_TO_APK`.

**Never commit or upload** `*.jks`, `*.keystore`, `keystore.properties`, or the
base64 text as repository files/GitHub artifacts. Local secret files are ignored;
CI uses ephemeral runner workspaces. Keep the keystore and passwords backed up
securely; losing the upload key requires a Play Console recovery process.

## Building locally

Requirements: JDK 17, Android SDK (platform 36). Android Studio Ladybug or newer
works out of the box.

```bash
./gradlew testDebugUnitTest     # unit tests
./gradlew assembleDebug         # debug APK  → app/build/outputs/apk/debug/
./gradlew assembleRelease       # requires keystore.properties; never debug-signed
./gradlew bundleRelease         # requires keystore.properties; upload-key signed
```

Install the debug APK on a connected device: `adb install app/build/outputs/apk/debug/app-debug.apk`

## Project layout

```
app/src/main/java/com/mbbshandbook/app/
├── MainActivity.kt          # navigation host + consent-gated ads startup
├── ads/                     # publisher IDs, UMP consent gate, banner + native composables
├── Bookmarks.kt             # persistent bookmarks
├── Progress.kt              # reading progress + best quiz scores
├── data/
│   ├── Model.kt             # Section / Topic / ContentBlock
│   ├── HandbookData.kt      # core handbook content
│   ├── HandbookExtras.kt    # extra topics appended per section
│   ├── StudyTools.kt        # quizzes, flashcards, glossary (base + merged bank)
│   ├── QuizBank.kt          # extra questions for the base categories
│   ├── QuizBankNew.kt       # Paeds / OB-GYN / Psychiatry / Emergency quizzes
│   ├── Specialties.kt       # Specialty model + catalogue lookups
│   ├── SpecialtiesMedical.kt / SpecialtiesProcedural.kt / SpecialtiesAcuteDx.kt
├── logic/
│   └── Calculators.kt       # pure, unit-tested clinical calculators
└── ui/
    ├── Screens.kt           # home, section, topic, search, bookmarks
    ├── StudyScreens.kt      # quizzes, flashcards, calculators, glossary
    ├── SpecialtyScreens.kt  # specialty catalogue + roadmap detail
    ├── Visuals.kt           # per-section gradient accents
│   ├── FintechUi.kt         # glow panels, pill buttons, tiles, bottom nav
    └── theme/               # Material 3 dark scheme + Poppins typography
```

To add or edit content, change the `data/` files — plain Kotlin data, with unit
tests verifying IDs, quiz validity, specialty roadmaps and calculator maths.

## Tech stack

- Kotlin 2.3, Jetpack Compose (BOM 2025.06), Material 3
- Navigation Compose (animated transitions), AndroidX Activity (edge-to-edge)
- AGP 8.9, Gradle 8.11, JDK 17 · minSdk 24 · compile/targetSdk 36
- Google Mobile Ads SDK (legacy) + UMP consent, publisher ad IDs
- R8 minification + resource shrinking for release builds
