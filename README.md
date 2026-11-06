# MBBS Students Handbook 📱🩺

A beautiful, offline-first Android handbook for MBBS students — study strategies for
every phase of medical school, clinical skills, exam technique, ethics, wellbeing,
plus interactive study tools.

Built with **Kotlin + Jetpack Compose (Material 3)** and custom **Poppins**
typography. The UI follows a dark fintech-inspired design: near-black teal
surfaces, glowing gradient progress card, pill buttons and filter chips,
cyan-accented bottom navigation, and animated transitions. No account. Core
handbook content works offline; the optional AdMob banner is currently in
**test-ad mode** and needs a network connection.

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
- Core content works **offline** (min Android 7.0 / API 24); test ad banners need network

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

## 🧪 Ads (AdMob test mode)

The app integrates the Google Mobile Ads SDK **with Google's official test IDs
only**:

- App ID: `ca-app-pub-3940256099942544~3347511713`
- Banner unit: `ca-app-pub-3940256099942544/9214589741`

Banners are shown only on browse/catalog screens (home, section lists,
specialties, quiz/flashcard/calculator/glossary catalogs, search and bookmarks).
The existing bottom banner remains, and an inline test banner is inserted after
every four data rows while more rows follow. Headers/intro text do not count as
rows. Ads are **not** shown while reading a topic, taking a quiz, flipping
flashcards, or using a calculator. A UMP consent gate runs before ads initialize;
if consent is unavailable, the app hides ads without blocking handbook content.
Banners are attached and measured before loading; inline slots reserve a real
measurement internally but occupy no list space until an actual test ad fills.
Unit tests guard test-only IDs, consent/init sequencing, duplicate-request
prevention, and the four-row placement rule.

**If test ads do not appear:** install the **debug APK**, connect to the internet,
and check the small status row above the bottom navigation. It reports privacy
check, SDK initialization, or banner-load errors and offers **Retry**. Filter
Logcat by `HandbookAds` for the SDK error code. Google's test creatives still
require a network request; they are not bundled fake banners. Complete any
privacy form shown. Release builds keep failed slots hidden and do not expose
debug diagnostics. Scroll down to see inline ads after rows 4, 8, …; short lists
with no following row intentionally have no trailing inline ad.

Before any live monetization: create your own AdMob app/ad unit, replace the IDs
in `AndroidManifest.xml` and `ads/AdsConfig.kt`, configure UMP privacy messages,
publish a privacy policy, and complete Play Data Safety + ads declarations. See
[`docs/PLAYSTORE.md`](docs/PLAYSTORE.md).

## CI artifacts (GitHub Actions)

Every push runs [`.github/workflows/android-build.yml`](.github/workflows/android-build.yml),
which runs the unit tests and then builds **all three artifacts**:

| Artifact | Gradle task | Download name |
|---|---|---|
| Debug APK | `assembleDebug` | `mbbshandbook-debug-apk` |
| Release APK (minified + resource-shrunk) | `assembleRelease` | `mbbshandbook-release-apk` |
| Release Android App Bundle | `bundleRelease` | `mbbshandbook-release-aab` |

Download them from the **Actions tab → latest run → Artifacts**.
Pushing a tag like `v1.0.0` also attaches all three files to a GitHub Release.

## Play Store readiness

The app is **not production-upload ready yet**. It now targets API 36 and has
test ads, but release signing, privacy policy/Data Safety, store listing assets,
content rating, and (for new personal accounts) closed testing still need to be
completed. Checklist: [`docs/PLAYSTORE.md`](docs/PLAYSTORE.md).

## Release signing

By default (no secrets configured) release artifacts are **debug-signed** so CI
always produces installable test builds. Debug-signed builds are **not** accepted
by the Play Store and cannot upgrade an existing release-signed install.

To sign with your own release key:

1. Create a keystore (keep it safe — you need the same key for every future update):

   ```bash
   keytool -genkeypair -v -keystore release-keystore.jks -alias mbbskey \
     -keyalg RSA -keysize 2048 -validity 10000
   ```

2. Base64-encode it: `base64 -w0 release-keystore.jks` (macOS: `base64 -i release-keystore.jks`)

3. Add these **repository secrets** (Settings → Secrets and variables → Actions):

   | Secret | Value |
   |---|---|
   | `KEYSTORE_BASE64` | the base64 string from step 2 |
   | `KEYSTORE_PASSWORD` | keystore password |
   | `KEY_ALIAS` | e.g. `mbbskey` |
   | `KEY_PASSWORD` | key password |

The next CI run signs the release APK and AAB with your key automatically.
**Never commit** `*.jks` / `keystore.properties` — they are gitignored.

## Building locally

Requirements: JDK 17, Android SDK (platform 36). Android Studio Ladybug or newer
works out of the box.

```bash
./gradlew testDebugUnitTest     # unit tests
./gradlew assembleDebug         # debug APK  → app/build/outputs/apk/debug/
./gradlew assembleRelease       # release APK → app/build/outputs/apk/release/
./gradlew bundleRelease         # release AAB → app/build/outputs/bundle/release/
```

Install the debug APK on a connected device: `adb install app/build/outputs/apk/debug/app-debug.apk`

## Project layout

```
app/src/main/java/com/mbbshandbook/app/
├── MainActivity.kt          # navigation host + consent-gated ads startup
├── ads/                     # AdMob test IDs, UMP consent gate, banner composable
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
- Google Mobile Ads SDK (legacy) + UMP consent, test IDs only
- R8 minification + resource shrinking for release builds
