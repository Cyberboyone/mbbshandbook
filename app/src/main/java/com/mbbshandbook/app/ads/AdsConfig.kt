package com.mbbshandbook.app.ads

/**
 * AdMob configuration.
 *
 * IMPORTANT: these are Google's official sample/test IDs. They must remain in
 * place while the app is in test-ad mode. Swapping in a real publisher ID is a
 * deliberate release step, not something that should happen by accident.
 */
object AdsConfig {
    const val TEST_ADMOB_APP_ID = "ca-app-pub-3940256099942544~3347511713"
    const val TEST_BANNER_AD_UNIT_ID = "ca-app-pub-3940256099942544/9214589741"

    /** Guard used by tests and release checklists to keep live ads disabled. */
    const val TEST_ADS_ONLY = true

    /** Show an inline test banner after this many data rows in browse lists. */
    const val INLINE_AD_ROW_INTERVAL = 4

    /** Keep inline list banners discreet instead of letting them grow very tall. */
    const val INLINE_AD_MAX_HEIGHT_DP = 120
}
