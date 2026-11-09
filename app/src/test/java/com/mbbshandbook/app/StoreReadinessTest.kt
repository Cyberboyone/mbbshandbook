package com.mbbshandbook.app

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.File

/**
 * Guards the store-facing contract: privacy policy link, support link and the
 * manifest hardening Play reviewers look at (no cleartext traffic, backup rules,
 * AD_ID declaration, exported launcher activity).
 */
class StoreReadinessTest {

    private fun manifest(): String {
        val candidates = listOf(
            File("src/main/AndroidManifest.xml"),
            File("app/src/main/AndroidManifest.xml")
        )
        val file = candidates.firstOrNull { it.exists() }
            ?: error("AndroidManifest.xml not found from ${File(".").absolutePath}")
        return file.readText()
    }

    @Test
    fun `privacy policy url is a public https page`() {
        assertTrue(Legal.PRIVACY_POLICY_URL.startsWith("https://"))
        assertFalse("Play rejects PDF privacy policies", Legal.PRIVACY_POLICY_URL.endsWith(".pdf"))
        assertFalse(Legal.PRIVACY_POLICY_URL.contains(" "))
        assertTrue(Legal.SUPPORT_URL.startsWith("https://"))
    }

    @Test
    fun `manifest advertises the same privacy policy url`() {
        val text = manifest()
        assertTrue(text.contains("com.mbbshandbook.app.PRIVACY_POLICY_URL"))
        assertTrue(text.contains("android:value=\"${Legal.PRIVACY_POLICY_URL}\""))
    }

    @Test
    fun `manifest blocks cleartext traffic`() {
        val text = manifest()
        assertTrue(text.contains("android:usesCleartextTraffic=\"false\""))
        assertTrue(text.contains("android:networkSecurityConfig=\"@xml/network_security_config\""))
    }

    @Test
    fun `manifest declares backup rules and ad id permission`() {
        val text = manifest()
        assertTrue(text.contains("android:fullBackupContent=\"@xml/backup_rules\""))
        assertTrue(text.contains("android:dataExtractionRules=\"@xml/data_extraction_rules\""))
        assertTrue(text.contains("com.google.android.gms.permission.AD_ID"))
    }

    @Test
    fun `launcher activity stays exported with a main launcher intent filter`() {
        val text = manifest()
        assertTrue(text.contains("android:exported=\"true\""))
        assertTrue(text.contains("android.intent.action.MAIN"))
        assertTrue(text.contains("android.intent.category.LAUNCHER"))
    }

    @Test
    fun `disclaimer and advertising notice are present`() {
        assertTrue(Legal.DISCLAIMER.contains("not medical advice"))
        assertTrue(Legal.ADVERTISING_NOTICE.contains("AdMob"))
        assertTrue(Legal.PRIVACY_POLICY_URL.endsWith(".html"))
        assertEquals(false, Legal.PRIVACY_POLICY_URL.contains(" "))
    }
}
