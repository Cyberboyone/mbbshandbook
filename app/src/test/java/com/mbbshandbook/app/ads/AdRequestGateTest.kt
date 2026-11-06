package com.mbbshandbook.app.ads

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class AdRequestGateTest {
    @Test
    fun `consent denial never initializes or permits ads`() {
        val gate = AdRequestGate()
        assertFalse(gate.beginInitialization(canRequestAds = false))
        assertFalse(gate.canLoadAds(canRequestAds = false))
        assertFalse(gate.isInitializing)
        assertFalse(gate.isInitialized)
    }

    @Test
    fun `cached consent and callbacks cannot initialize twice or load early`() {
        val gate = AdRequestGate()
        assertTrue(gate.beginInitialization(canRequestAds = true))
        assertFalse(gate.beginInitialization(canRequestAds = true))
        assertFalse(gate.canLoadAds(canRequestAds = true))
        gate.completeInitialization()
        assertFalse(gate.beginInitialization(canRequestAds = true))
        assertTrue(gate.canLoadAds(canRequestAds = true))
    }

    @Test
    fun `completion cannot override consent revoked during initialization`() {
        val gate = AdRequestGate()
        assertTrue(gate.beginInitialization(canRequestAds = true))
        gate.completeInitialization()
        assertFalse(gate.canLoadAds(canRequestAds = false))
        assertFalse(gate.beginInitialization(canRequestAds = false))
    }

    @Test
    fun `consent can be restored without restarting the process or SDK`() {
        val gate = AdRequestGate()
        assertFalse(gate.beginInitialization(canRequestAds = false))
        assertTrue(gate.beginInitialization(canRequestAds = true))
        gate.completeInitialization()
        assertFalse(gate.canLoadAds(canRequestAds = false))
        assertTrue(gate.canLoadAds(canRequestAds = true))
        assertFalse(gate.beginInitialization(canRequestAds = true))
    }
}
