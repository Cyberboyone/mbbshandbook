package com.mbbshandbook.app

import com.mbbshandbook.app.data.Specialties
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class SpecialtiesTest {

    @Test
    fun `catalogue is substantial with unique ids`() {
        assertTrue(Specialties.all.size >= 15)
        val ids = Specialties.all.map { it.id }
        assertEquals(ids.size, ids.toSet().size)
    }

    @Test
    fun `every specialty has complete content`() {
        Specialties.all.forEach { s ->
            assertTrue("${s.id}: blank name", s.name.isNotBlank())
            assertTrue("${s.id}: blank tagline", s.tagline.isNotBlank())
            assertTrue("${s.id}: blank overview", s.overview.isNotBlank())
            assertTrue("${s.id}: no day-in-life", s.dayInLife.isNotEmpty())
            assertTrue("${s.id}: no skills", s.keySkills.isNotEmpty())
            assertTrue("${s.id}: no rewards", s.rewards.isNotEmpty())
            assertTrue("${s.id}: no challenges", s.challenges.isNotEmpty())
            assertTrue("${s.id}: no lifestyle", s.lifestyle.isNotBlank())
            assertTrue("${s.id}: no exams", s.exams.isNotEmpty())
            assertTrue("${s.id}: no start-now steps", s.startNow.isNotEmpty())
        }
    }

    @Test
    fun `every roadmap has stages with details`() {
        Specialties.all.forEach { s ->
            assertTrue("${s.id}: roadmap too short", s.roadmap.size >= 3)
            s.roadmap.forEach { stage ->
                assertTrue("${s.id}: blank stage title", stage.title.isNotBlank())
                assertTrue("${s.id}: blank stage duration", stage.duration.isNotBlank())
                assertTrue("${s.id}: stage without details", stage.details.isNotEmpty())
            }
        }
    }

    @Test
    fun `categories cover the catalogue`() {
        assertTrue(Specialties.categories.isNotEmpty())
        Specialties.all.forEach { s ->
            assertTrue("${s.id} missing category", s.category in Specialties.categories)
        }
    }

    @Test
    fun `lookup and search work`() {
        val first = Specialties.all.first()
        assertNotNull(Specialties.byId(first.id))
        assertNull(Specialties.byId("no-such-specialty"))
        assertTrue(Specialties.search("SURGERY").isNotEmpty())
        assertTrue(Specialties.search("heart").isNotEmpty())
        assertEquals(Specialties.all.size, Specialties.search("").size)
        assertTrue(Specialties.search("zzz-no-such-term-zzz").isEmpty())
    }
}
