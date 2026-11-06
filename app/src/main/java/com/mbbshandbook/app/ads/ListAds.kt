package com.mbbshandbook.app.ads

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Renders list rows and attaches an inline test banner after every fourth data
 * row when more rows follow. The banner is kept inside the same lazy item as the
 * preceding row so an unfilled ad does not create an empty spacing slot.
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
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                itemContent(item)
                TestAdBanner(isInline = true)
            }
        } else {
            itemContent(item)
        }
    }
}
