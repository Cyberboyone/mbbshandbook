package com.mbbshandbook.app.ads

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Renders list rows with the existing inline banner after every fourth data
 * row when more rows follow. Callers may opt into one additional native card
 * after the sixth row. Ads share the preceding row's lazy item, so an unfilled
 * slot adds no item or separator. Headers are not counted as data rows.
 */
fun <T> LazyListScope.itemsWithInlineAds(
    items: List<T>,
    key: ((item: T) -> Any)? = null,
    nativeAdPlacement: String? = null,
    itemContent: @Composable (item: T) -> Unit
) {
    itemsIndexed(
        items = items,
        key = key?.let { itemKey -> { _: Int, item: T -> itemKey(item) } }
    ) { index, item ->
        val showBanner = ListAdPlacement.shouldShowAfter(index, items.size)
        val showNative = nativeAdPlacement != null &&
            ListAdPlacement.shouldShowNativeAfter(index, items.size)
        if (showBanner || showNative) {
            Column {
                itemContent(item)
                if (showNative) {
                    NativeAdvancedAd(placement = requireNotNull(nativeAdPlacement))
                }
                if (showBanner) AdBanner(isInline = true)
            }
        } else {
            itemContent(item)
        }
    }
}
