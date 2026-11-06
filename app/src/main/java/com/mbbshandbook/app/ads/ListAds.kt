package com.mbbshandbook.app.ads

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Renders list rows and attaches an inline banner after every fourth data
 * row when more rows follow. The banner is kept inside the same lazy item as the
 * preceding row. The banner supplies its own separator only after a real fill,
 * so an unfilled slot occupies no space.
 */
fun <T> LazyListScope.itemsWithInlineAds(
    items: List<T>,
    key: ((item: T) -> Any)? = null,
    itemContent: @Composable (item: T) -> Unit
) {
    itemsIndexed(
        items = items,
        key = key?.let { itemKey -> { _: Int, item: T -> itemKey(item) } }
    ) { index, item ->
        if (ListAdPlacement.shouldShowAfter(index, items.size)) {
            Column(modifier = Modifier.fillMaxWidth()) {
                itemContent(item)
                AdBanner(isInline = true)
            }
        } else {
            itemContent(item)
        }
    }
}
