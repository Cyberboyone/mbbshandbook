package com.mbbshandbook.app.ads

import org.junit.Assert.assertEquals
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

    @Test
    fun `one native card follows the sixth data row only when more content follows`() {
        assertTrue(ListAdPlacement.shouldShowNativeAfter(zeroBasedIndex = 5, rowCount = 7))
        assertTrue(ListAdPlacement.shouldShowNativeAfter(zeroBasedIndex = 5, rowCount = 64))
        assertFalse(ListAdPlacement.shouldShowNativeAfter(zeroBasedIndex = 5, rowCount = 6))
        assertFalse(ListAdPlacement.shouldShowNativeAfter(zeroBasedIndex = 6, rowCount = 7))
    }

    @Test
    fun `native placement is distinct from the unchanged banner cadence`() {
        val rowCount = 64
        val nativeRows = (0 until rowCount).filter {
            ListAdPlacement.shouldShowNativeAfter(it, rowCount)
        }
        assertEquals(listOf(5), nativeRows)
        nativeRows.forEach { index ->
            assertFalse(ListAdPlacement.shouldShowAfter(index, rowCount))
        }
        assertTrue(ListAdPlacement.shouldShowAfter(3, rowCount))
        assertTrue(ListAdPlacement.shouldShowAfter(7, rowCount))
    }

    @Test
    fun `empty short and invalid lists never get a native card`() {
        listOf(-1, 0, 1, 5, 6).forEach { count ->
            (-1..count).forEach { index ->
                assertFalse(ListAdPlacement.shouldShowNativeAfter(index, count))
            }
        }
        assertFalse(ListAdPlacement.shouldShowNativeAfter(-1, 10))
        assertFalse(ListAdPlacement.shouldShowNativeAfter(10, 10))
    }
}
