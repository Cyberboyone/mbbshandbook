package com.mbbshandbook.app.ads

/**
 * Pure placement rule for inline list ads.
 *
 * Ads appear after every [AdsConfig.INLINE_AD_ROW_INTERVAL] data rows, but not
 * after the final row when there is no more content below it. Headers/intro text
 * should not be counted as data rows by callers.
 */
object ListAdPlacement {
    fun shouldShowAfter(
        zeroBasedIndex: Int,
        rowCount: Int,
        interval: Int = AdsConfig.INLINE_AD_ROW_INTERVAL
    ): Boolean {
        if (interval <= 0 || rowCount <= 0) return false
        if (zeroBasedIndex < 0 || zeroBasedIndex >= rowCount - 1) return false
        return (zeroBasedIndex + 1) % interval == 0
    }
}
