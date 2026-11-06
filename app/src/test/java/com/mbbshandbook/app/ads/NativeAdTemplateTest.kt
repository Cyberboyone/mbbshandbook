package com.mbbshandbook.app.ads

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.File
import javax.xml.parsers.DocumentBuilderFactory
import org.w3c.dom.Element

/** Source/resource guards for the SDK-native template used inside Compose. */
class NativeAdTemplateTest {
    private fun projectFile(path: String): File = listOf(File(path), File("app/$path"))
        .firstOrNull { it.exists() } ?: error("Missing $path")

    @Test
    fun `every native asset is a descendant of the SDK NativeAdView`() {
        val document = DocumentBuilderFactory.newInstance().newDocumentBuilder()
            .parse(projectFile("src/main/res/layout/native_advanced_ad.xml"))
        val root = document.documentElement
        assertEquals("com.google.android.gms.ads.nativead.NativeAdView", root.tagName)
        val ids = listOf(
            "native_headline", "native_icon", "native_media", "native_body",
            "native_advertiser", "native_stars", "native_price", "native_store",
            "native_call_to_action"
        )
        ids.forEach { id ->
            val matches = document.getElementsByTagName("*").let { nodes ->
                (0 until nodes.length).map { nodes.item(it) as Element }
                    .filter { it.getAttribute("android:id") == "@+id/$id" }
            }
            assertEquals("One view for $id", 1, matches.size)
            var parent = matches.single().parentNode
            var insideRoot = false
            while (parent != null) {
                if (parent === root) insideRoot = true
                parent = parent.parentNode
            }
            assertTrue("$id must be inside NativeAdView", insideRoot)
        }
        val source = projectFile(
            "src/main/java/com/mbbshandbook/app/ads/NativeAdvancedAd.kt"
        ).readText()
        val bind = source.substringAfter("private fun bindNativeAd(")
            .substringBefore("private fun bindText(")
        assertTrue(bind.indexOf("view.setNativeAd(ad)") > bind.lastIndexOf("View = "))
        assertFalse("SDK must own native asset clicks", bind.contains("setOnClickListener"))
        assertTrue(source.contains("AdsConfig.NATIVE_ADVANCED_AD_UNIT_ID"))
        assertFalse(source.contains("AdsConfig.BANNER_AD_UNIT_ID"))
    }

    @Test
    fun `native template is clearly labeled and reserves AdChoices space`() {
        val layout = projectFile("src/main/res/layout/native_advanced_ad.xml").readText()
        val strings = projectFile("src/main/res/values/native_ads.xml").readText()
        assertTrue(layout.contains("@string/native_ad_badge"))
        assertTrue(layout.contains("@string/native_ad_attribution"))
        assertTrue(strings.contains("<string name=\"native_ad_badge\">Ad</string>"))
        assertTrue(strings.contains("<string name=\"native_ad_attribution\">Advertisement</string>"))
        assertTrue(layout.contains("android:layout_width=\"24dp\""))
        assertTrue(layout.contains("android:layout_height=\"24dp\""))
        val source = projectFile(
            "src/main/java/com/mbbshandbook/app/ads/NativeAdvancedAd.kt"
        ).readText()
        assertTrue(source.contains("NativeAdOptions.ADCHOICES_TOP_RIGHT"))
        assertTrue(source.contains("setStartMuted(true)"))
    }

    @Test
    fun `native requests are consent gated and received ads are destroyed`() {
        val source = projectFile(
            "src/main/java/com/mbbshandbook/app/ads/NativeAdvancedAd.kt"
        ).readText()
        assertTrue(source.contains("if (!AdsController.adsReady)"))
        assertTrue(source.contains("disposed || !AdsController.adsReady"))
        assertTrue(source.contains("ad.destroy()"))
        assertTrue(source.contains("receivedAd?.destroy()"))
        assertTrue(source.contains("BuildConfig.DEBUG"))
        assertTrue(source.contains("Lifecycle.Event.ON_PAUSE"))
    }

    @Test
    fun `native cards are opt-in for catalogs never active study or detail flows`() {
        val directory = listOf(
            File("src/main/java/com/mbbshandbook/app/ui"),
            File("app/src/main/java/com/mbbshandbook/app/ui")
        ).first { it.exists() }
        val sourceFiles = requireNotNull(directory.listFiles())
        val calls = sourceFiles.filter { file -> file.extension == "kt" }
            .flatMap { file -> file.readLines() }
            .filter { line -> line.contains("nativeAdPlacement =") }
        assertEquals(8, calls.size)
        val specialty = File(directory, "SpecialtyScreens.kt").readText()
        val catalog = specialty.substringBefore("fun SpecialtyDetailScreen(")
        val detail = specialty.substringAfter("fun SpecialtyDetailScreen(")
        assertTrue(catalog.contains("NativeAdvancedAd(placement = \"specialties\")"))
        assertFalse(detail.contains("NativeAdvancedAd"))
        assertFalse(detail.contains("itemsWithInlineAds"))
        listOf("Screens.kt", "StudyScreens.kt").forEach { name ->
            val text = File(directory, name).readText()
            text.lines().filter { it.contains("itemsWithInlineAds(") }.forEach {
                assertTrue("Catalog call must explicitly opt into native: $it", it.contains("nativeAdPlacement ="))
            }
        }
    }
}
