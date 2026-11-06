package com.mbbshandbook.app.ads

/**
 * AdMob configuration for MBBS Handbook.
 *
 * These publisher IDs were explicitly supplied for the v1.4.0 ad rollout.
 * The app ID is declared in AndroidManifest.xml; the banner unit is used by
 * both anchored bottom banners and inline adaptive list banners.
 *
 * When validating live ads, register your device as an AdMob test device and
 * never click your own live advertisements.
 */
object AdsConfig {
    const val ADMOB_APP_ID = "ca-app-pub-9529770421530115~3987689260"
    const val BANNER_AD_UNIT_ID = "ca-app-pub-9529770421530115/5109199243"

    /** Publisher IDs are enabled; this is no longer the sample/test-only mode. */
    const val LIVE_ADS_ENABLED = true

    /** Show an inline banner after this many data rows in browse lists. */
    const val INLINE_AD_ROW_INTERVAL = 4

    /** Keep inline list banners discreet instead of letting them grow very tall. */
    const val INLINE_AD_MAX_HEIGHT_DP = 120
}
