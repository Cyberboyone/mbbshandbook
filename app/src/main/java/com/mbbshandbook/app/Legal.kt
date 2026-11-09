package com.mbbshandbook.app

/**
 * Legal / store-facing links used by the About & privacy screen.
 *
 * [PRIVACY_POLICY_URL] must stay in sync with the manifest meta-data
 * (`com.mbbshandbook.app.PRIVACY_POLICY_URL`) and with the URL entered in the
 * Play Console listing — a unit test guards that. It must be a public HTTPS
 * page: Play rejects PDFs, logins and unreachable hosts.
 */
object Legal {
    const val PRIVACY_POLICY_URL =
        "https://cyberboyone.github.io/mbbshandbook/privacy-policy.html"

    const val SUPPORT_URL =
        "https://github.com/Cyberboyone/mbbshandbook/issues"

    const val DISCLAIMER =
        "MBBS Handbook is an educational study aid for medical students. It is not " +
            "medical advice, not a clinical decision tool and not a substitute for your " +
            "curriculum, your supervisors or current local guidelines. Always verify doses " +
            "and protocols before clinical use."

    const val ADVERTISING_NOTICE =
        "Ads are served by Google AdMob. Where required, you are asked for consent first; " +
            "if consent is unavailable, ads are hidden and every handbook feature keeps working. " +
            "Bookmarks, reading progress and quiz scores stay on your device."
}
