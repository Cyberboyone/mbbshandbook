# MBBS Students Handbook 📱🩺

A beautiful, offline-first Android handbook for MBBS students — study strategies for
every phase of medical school, clinical skills, exam technique, ethics, wellbeing,
plus interactive study tools.

Built with **Kotlin + Jetpack Compose (Material 3)**, custom **Poppins** typography,
gradient UI, illustrated banners, and animated transitions. No account, no internet,
no tracking.

## ✨ Features

### 📖 Handbook — 11 sections, 67 topics
Getting started · Preclinical · Paraclinical · Clinical rotations · Clinical skills ·
Exam success · Ethics · Wellbeing · Resources & career · **Mnemonics vault** ·
**Emergency quick reference** — each section with its own illustrated banner and
gradient theme. Includes ECG/CXR/ABG interpretation, lab patterns, ward calls,
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
- **Dark mode** + edge-to-edge design, animated navigation
- Works fully **offline** (min Android 7.0 / API 24)

> ⚠️ Educational guidance for students — not medical advice. Always follow your
> school's curriculum, your supervisors and current local clinical guidelines.

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

Requirements: JDK 17, Android SDK (platform 35). Android Studio Koala or newer
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
├── MainActivity.kt          # navigation host with animated transitions
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
    ├── Visuals.kt           # banner art + per-section gradients
    └── theme/               # Material 3 schemes + Poppins typography
```

Illustrated banners live in `res/drawable-nodpi/` (optimized WebP).
To add or edit content, change the `data/` files — plain Kotlin data, with unit
tests verifying IDs, quiz validity, specialty roadmaps and calculator maths.

## Tech stack

- Kotlin 2.0, Jetpack Compose (BOM 2024.09), Material 3
- Navigation Compose (animated transitions), AndroidX Activity (edge-to-edge)
- AGP 8.6, Gradle 8.9, JDK 17 · minSdk 24 · targetSdk 35
- R8 minification + resource shrinking for release builds
