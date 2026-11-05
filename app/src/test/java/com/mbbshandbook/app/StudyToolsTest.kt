package com.mbbshandbook.app

import com.mbbshandbook.app.data.StudyTools
import com.mbbshandbook.app.logic.Calculators
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class StudyToolsTest {

    @Test
    fun `quiz categories are valid`() {
        assertTrue(StudyTools.quizCategories.isNotEmpty())
        val ids = StudyTools.quizCategories.map { it.id }
        assertEquals(ids.size, ids.toSet().size)
        StudyTools.quizCategories.forEach { cat ->
            assertTrue(cat.questions.size >= 5)
            cat.questions.forEach { q ->
                assertEquals(4, q.options.size)
                assertTrue(q.correctIndex in 0..3)
                assertTrue(q.explanation.isNotBlank())
            }
        }
    }

    @Test
    fun `flashcard decks are valid`() {
        assertTrue(StudyTools.flashcardDecks.isNotEmpty())
        StudyTools.flashcardDecks.forEach { deck ->
            assertTrue(deck.cards.isNotEmpty())
            deck.cards.forEach {
                assertTrue(it.front.isNotBlank())
                assertTrue(it.back.isNotBlank())
            }
        }
    }

    @Test
    fun `glossary is sorted and non-empty`() {
        assertTrue(StudyTools.glossary.size >= 30)
        val terms = StudyTools.glossary.map { it.term.lowercase() }
        assertEquals(terms, terms.sorted())
    }

    @Test
    fun `bmi calculation`() {
        val r = Calculators.bmi(70.0, 175.0)
        assertNotNull(r)
        assertEquals(22.9, r!!.bmi, 0.05)
        assertEquals("Healthy range", r.category)
        assertNull(Calculators.bmi(0.0, 175.0))
        assertNull(Calculators.bmi(70.0, 0.0))
    }

    @Test
    fun `drip rate calculation`() {
        // 1000 mL over 8 h with 20 gtt/mL = 41.67 -> 42 gtt/min
        assertEquals(42, Calculators.dripRate(1000.0, 480.0, 20))
        assertEquals(125.0, Calculators.infusionRate(1000.0, 480.0)!!, 0.01)
        assertNull(Calculators.dripRate(1000.0, 0.0, 20))
    }

    @Test
    fun `maintenance fluids 4-2-1`() {
        assertEquals(40.0, Calculators.maintenanceFluidMlPerHr(10.0)!!, 0.01)
        assertEquals(60.0, Calculators.maintenanceFluidMlPerHr(20.0)!!, 0.01)
        assertEquals(110.0, Calculators.maintenanceFluidMlPerHr(70.0)!!, 0.01)
        assertNull(Calculators.maintenanceFluidMlPerHr(-5.0))
    }

    @Test
    fun `gcs scoring`() {
        assertEquals(15, Calculators.gcs(4, 5, 6)!!.total)
        assertEquals("Severe impairment", Calculators.gcs(1, 1, 1)!!.severity)
        assertNull(Calculators.gcs(5, 5, 6))
    }

    @Test
    fun `apgar scoring`() {
        assertEquals(10, Calculators.apgar(listOf(2, 2, 2, 2, 2))!!.total)
        assertEquals("Reassuring", Calculators.apgar(listOf(2, 2, 1, 1, 1))!!.interpretation)
        assertNull(Calculators.apgar(listOf(2, 2, 2)))
        assertNull(Calculators.apgar(listOf(3, 2, 2, 2, 2)))
    }
}
