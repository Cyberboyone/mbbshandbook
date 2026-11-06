package com.mbbshandbook.app.ads

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.File

class AdsConfigTest {
    @Test
    fun `ads remain locked to Google sample test IDs`() {
        assertTrue("Test-ad mode must stay enabled until a deliberate live-ads release", AdsConfig.TEST_ADS_ONLY)
        assertEquals("ca-app-pub-3940256099942544~3347511713", AdsConfig.TEST_ADMOB_APP_ID)
        assertEquals("ca-app-pub-3940256099942544/9214589741", AdsConfig.TEST_BANNER_AD_UNIT_ID)
        assertEquals(4, AdsConfig.INLINE_AD_ROW_INTERVAL)
        assertTrue(AdsConfig.INLINE_AD_MAX_HEIGHT_DP > 0)
    }

    @Test
    fun `manifest declares the test AdMob application ID`() {
        val candidates = listOf(
            File("src/main/AndroidManifest.xml"),
            File("app/src/main/AndroidManifest.xml")
        )
        val manifest = candidates.firstOrNull { it.exists() }
            ?: error("AndroidManifest.xml not found from ${File(".").absolutePath}")
        val text = manifest.readText()
        assertTrue(text.contains(AdsConfig.TEST_ADMOB_APP_ID))
        assertTrue(text.contains("com.google.android.gms.ads.APPLICATION_ID"))
    }
}
