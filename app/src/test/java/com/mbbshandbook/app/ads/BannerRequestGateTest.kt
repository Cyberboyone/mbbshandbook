package com.mbbshandbook.app.ads

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class BannerRequestGateTest {
    @Test
    fun `detached zero-sized or disallowed views never request`() {
        val gate = BannerRequestGate()
        assertFalse(gate.beginRequest(0, 0, true, false, 1080, 150))
        assertFalse(gate.beginRequest(0, 0, true, true, 0, 150))
        assertFalse(gate.beginRequest(0, 0, true, true, 1080, 0))
        assertFalse(gate.beginRequest(0, 0, false, true, 1080, 150))
    }

    @Test
    fun `layout callbacks do not duplicate a request`() {
        val gate = BannerRequestGate()
        assertTrue(gate.beginRequest(0, 0, true, true, 1080, 150))
        assertFalse(gate.beginRequest(0, 0, true, true, 1080, 150))
        assertFalse(gate.beginRequest(0, 0, true, true, 1080, 360))
    }

    @Test
    fun `explicit retry can request again without restarting the app`() {
        val gate = BannerRequestGate()
        assertTrue(gate.beginRequest(0, 0, true, true, 1080, 150))
        assertTrue(gate.beginRequest(0, 1, true, true, 1080, 150))
        assertTrue(gate.beginRequest(1, 1, true, true, 1080, 150))
    }

    @Test
    fun `consent is checked again on every retry`() {
        val gate = BannerRequestGate()
        assertTrue(gate.beginRequest(0, 0, true, true, 1080, 150))
        assertFalse(gate.beginRequest(1, 0, false, true, 1080, 150))
        assertTrue(gate.beginRequest(2, 0, true, true, 1080, 150))
    }
}
