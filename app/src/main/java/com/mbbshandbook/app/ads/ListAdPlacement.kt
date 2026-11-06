package com.mbbshandbook.app.ads

/**
 * Pure placement rules for inline banners and additional native cards.
 *
 * Ads appear after every [AdsConfig.INLINE_AD_ROW_INTERVAL] data rows, but not
 * after the final row when there is no more content below it. Headers/intro text
 * should not be counted as data rows by callers.
 */
object ListAdPlacement {
    /**
     * One native card after the sixth data row, never after the final row.
     * This offsets native cards from the unchanged four-row banner cadence.
     */
    fun shouldShowNativeAfter(zeroBasedIndex: Int, rowCount: Int): Boolean =
        rowCount > AdsConfig.NATIVE_AD_AFTER_ROW &&
            zeroBasedIndex == AdsConfig.NATIVE_AD_AFTER_ROW - 1

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
