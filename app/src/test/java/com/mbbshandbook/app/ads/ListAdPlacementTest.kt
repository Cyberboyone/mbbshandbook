package com.mbbshandbook.app.ads

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ListAdPlacementTest {
    @Test
    fun `inline ads appear after every four data rows when more rows follow`() {
        assertTrue(ListAdPlacement.shouldShowAfter(zeroBasedIndex = 3, rowCount = 5))
        assertTrue(ListAdPlacement.shouldShowAfter(zeroBasedIndex = 3, rowCount = 9))
        assertTrue(ListAdPlacement.shouldShowAfter(zeroBasedIndex = 7, rowCount = 9))
        assertTrue(ListAdPlacement.shouldShowAfter(zeroBasedIndex = 11, rowCount = 13))
    }

    @Test
    fun `no inline ad after the final row or between intervals`() {
        assertFalse(ListAdPlacement.shouldShowAfter(zeroBasedIndex = 3, rowCount = 4))
        assertFalse(ListAdPlacement.shouldShowAfter(zeroBasedIndex = 7, rowCount = 8))
        assertFalse(ListAdPlacement.shouldShowAfter(zeroBasedIndex = 0, rowCount = 9))
        assertFalse(ListAdPlacement.shouldShowAfter(zeroBasedIndex = 4, rowCount = 9))
        assertFalse(ListAdPlacement.shouldShowAfter(zeroBasedIndex = 8, rowCount = 9))
    }

    @Test
    fun `invalid inputs never place ads`() {
        assertFalse(ListAdPlacement.shouldShowAfter(zeroBasedIndex = -1, rowCount = 9))
        assertFalse(ListAdPlacement.shouldShowAfter(zeroBasedIndex = 3, rowCount = 0))
        assertFalse(ListAdPlacement.shouldShowAfter(zeroBasedIndex = 3, rowCount = 9, interval = 0))
        assertFalse(ListAdPlacement.shouldShowAfter(zeroBasedIndex = 3, rowCount = 9, interval = -4))
    }
}
