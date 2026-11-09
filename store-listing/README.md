# Play Store listing assets

Everything Google Play needs for the MBBS Handbook listing, plus what still has to
be captured from a real device.

| Asset | File | Status |
|---|---|---|
| App icon (512×512, 32-bit PNG ≤ 1 MB) | `icon-512.png` | ✅ done |
| Feature graphic (1024×500, no transparency, ≤ 15 MB) | `feature-graphic-1024x500.png` | ✅ done — regenerate with `python3 scripts/generate_store_graphics.py` |
| Phone screenshots (2–8, PNG/JPEG, 320–3840 px) | `screenshots/` | ⬜ capture on a device |
| Tablet screenshots (7"/10") | — | ⬜ optional; only if you list tablet support |
| Promo video (YouTube) | — | ⬜ optional |
| Title / short / full description | `fastlane/metadata/android/en-US/*.txt` | ✅ done |
| Release notes | `fastlane/metadata/android/en-US/changelogs/<versionCode>.txt` | ✅ done for versionCode 9 |

`fastlane/` mirrors these files in the layout that Fastlane/`supply` and the
**Play Release** GitHub workflow expect, so automated uploads pick them up:
`fastlane/metadata/android/en-US/images/icon.png`,
`images/featureGraphic.png`, `images/phoneScreenshots/`.

## Capturing screenshots

Screenshots must show the real app — Play rejects mock-ups that misrepresent the
product. With the debug APK installed on a phone (or emulator):

```bash
adb install -r app/build/outputs/apk/debug/app-debug.apk

# Capture a shot, then navigate and repeat:
adb exec-out screencap -p > store-listing/screenshots/01-home.png
```

Recommended set (in this order), portrait 1080×1920 or taller:

1. **Home** — progress card, quick actions, section list.
2. **Topic reading** — a handbook topic such as ECG basics.
3. **Quiz round** — a question with the explanation revealed.
4. **Flashcards** — a flipped card.
5. **Specialty roadmap** — the MBBS → residency timeline.
6. **Calculators** — Glasgow Coma Scale or maintenance fluids.
7. **Search** — results for a term.

Tips:
- Use a clean status bar (no personal notifications); Play rejects shots with
  personal data or another app's branding.
- Localise nothing for now — the app is English-only.
- Keep the screenshot set under 8 images; the first two are what most people see.
- Screenshots with ads are fine, but avoid a shot that is mostly an ad.

After capturing, copy the same files into
`fastlane/metadata/android/en-US/images/phoneScreenshots/` and run
`python3 scripts/check_store_listing.py` to re-validate the listing.
