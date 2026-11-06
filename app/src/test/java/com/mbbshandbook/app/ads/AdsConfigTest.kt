package com.mbbshandbook.app.ads

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.File

class AdsConfigTest {
    @Test
    fun `ads use the publisher-supplied AdMob IDs`() {
        assertTrue("Publisher IDs are explicitly enabled", AdsConfig.LIVE_ADS_ENABLED)
        assertEquals("ca-app-pub-9529770421530115~3987689260", AdsConfig.ADMOB_APP_ID)
        assertEquals("ca-app-pub-9529770421530115/5109199243", AdsConfig.BANNER_AD_UNIT_ID)
        assertEquals(
            "ca-app-pub-9529770421530115/8467833630",
            AdsConfig.NATIVE_ADVANCED_AD_UNIT_ID
        )
        assertFalse(AdsConfig.NATIVE_ADVANCED_AD_UNIT_ID.contains("3940256099942544"))
        assertFalse(AdsConfig.BANNER_AD_UNIT_ID == AdsConfig.NATIVE_ADVANCED_AD_UNIT_ID)
        assertFalse(AdsConfig.ADMOB_APP_ID.contains("3940256099942544"))
        assertFalse(AdsConfig.BANNER_AD_UNIT_ID.contains("3940256099942544"))
        assertEquals(4, AdsConfig.INLINE_AD_ROW_INTERVAL)
        assertTrue(AdsConfig.INLINE_AD_MAX_HEIGHT_DP > 0)
        assertEquals(6, AdsConfig.NATIVE_AD_AFTER_ROW)
    }

    @Test
    fun `manifest declares the same publisher AdMob application ID`() {
        val candidates = listOf(
            File("src/main/AndroidManifest.xml"),
            File("app/src/main/AndroidManifest.xml")
        )
        val manifest = candidates.firstOrNull { it.exists() }
            ?: error("AndroidManifest.xml not found from ${File(".").absolutePath}")
        val text = manifest.readText()
        assertTrue(text.contains("android:value=\"${AdsConfig.ADMOB_APP_ID}\""))
        assertTrue(text.contains("com.google.android.gms.ads.APPLICATION_ID"))
        assertFalse(text.contains("ca-app-pub-3940256099942544"))
    }
}
