# Play Console — Data safety answers

Copy these answers into **Play Console → App content → Data safety**. They match
`docs/privacy-policy.html` and the Google Mobile Ads SDK behaviour. Re-check against
[Google's AdMob data-safety guidance](https://support.google.com/admob/answer/11150250)
before submitting, and update this file whenever the app changes.

## Overview questions

| Question | Answer |
|---|---|
| Does your app collect or share any of the required user data types? | **Yes** (only through the Google Mobile Ads SDK) |
| Is all of the user data collected by your app encrypted in transit? | **Yes** — HTTPS only, cleartext traffic is disabled |
| Do you provide a way for users to request that their data is deleted? | **No** — the app has no accounts and stores no data on a server of ours; consent/ads data is controlled by Google and can be reset in Android Settings or via in-app *Ad privacy choices* |
| Do you have a privacy policy? | **Yes** — `https://cyberboyone.github.io/mbbshandbook/privacy-policy.html` |
| Have you committed to the Play Families policy? | **No** — the app is not directed to children |

## Data types

Tick **Collected** and **Shared** for each row below. "Shared" applies because the
Google Mobile Ads SDK transmits the data to Google and its advertising partners.

| Data type (Play category) | Collected | Shared | Processed ephemerally | Required or optional | Purpose |
|---|---|---|---|---|---|
| **Device or other IDs** → Device or other IDs | Yes | Yes | No | Required (ads are the app's only funding; content works without them) | Advertising or marketing; Fraud prevention, security and compliance |
| **Location** → Approximate location | Yes | Yes | No | Required | Advertising or marketing; Analytics |
| **App activity** → Other actions (ad impressions/clicks/video views) | Yes | Yes | No | Required | Advertising or marketing; Analytics; Fraud prevention, security and compliance |
| **App info and performance** → Diagnostics (SDK errors, crash/ANR traces from the ads SDK) | Yes | Yes | No | Required | Analytics; Fraud prevention, security and compliance |

Everything else: **not collected** — no name, email, phone, address, precise
location, contacts, photos, files, calendar, SMS, health data, financial info,
browsing history, in-app search history, installed apps, or user-generated content.

## Notes to stay consistent

- The app itself stores bookmarks, reading progress and quiz scores **on the
  device only**. On-device storage that is never transmitted is *not* "collected"
  for Data Safety purposes — but it *is* covered by the privacy policy, which
  mentions Android Backup.
- The Android Advertising ID permission (`com.google.android.gms.permission.AD_ID`)
  is declared in the manifest.
- Under **App content → Ads**, declare **Contains ads = Yes**.
- Keep the privacy policy URL identical in: Play listing, Data safety, the
  manifest meta-data and `Legal.kt` (a unit test guards the last two).
