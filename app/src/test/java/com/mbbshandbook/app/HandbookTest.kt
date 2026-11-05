package com.mbbshandbook.app

import com.mbbshandbook.app.data.Handbook
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class HandbookTest {

    @Test
    fun `sections are non-empty`() {
        assertTrue(Handbook.sections.isNotEmpty())
        Handbook.sections.forEach { section ->
            assertTrue("Section ${section.id} has no topics", section.topics.isNotEmpty())
        }
    }

    @Test
    fun `topic ids are unique`() {
        val ids = Handbook.allTopics.map { it.id }
        assertEquals(ids.size, ids.toSet().size)
    }

    @Test
    fun `section ids are unique`() {
        val ids = Handbook.sections.map { it.id }
        assertEquals(ids.size, ids.toSet().size)
    }

    @Test
    fun `every topic has content`() {
        Handbook.allTopics.forEach { topic ->
            assertTrue("Topic ${topic.id} has no blocks", topic.blocks.isNotEmpty())
            assertTrue("Topic ${topic.id} has blank title", topic.title.isNotBlank())
            assertTrue("Topic ${topic.id} has blank summary", topic.summary.isNotBlank())
        }
    }

    @Test
    fun `lookups work`() {
        val topic = Handbook.allTopics.first()
        assertNotNull(Handbook.topicById(topic.id))
        assertNotNull(Handbook.sectionForTopic(topic.id))
        val section = Handbook.sections.first()
        assertNotNull(Handbook.sectionById(section.id))
    }

    @Test
    fun `search finds anatomy and is case-insensitive`() {
        assertTrue(Handbook.search("ANATOMY").any { it.id == "anatomy" })
        assertTrue(Handbook.search("osce").isNotEmpty())
        assertTrue(Handbook.search("").isEmpty())
        assertTrue(Handbook.search("zzz-no-such-term-zzz").isEmpty())
    }

    @Test
    fun `expanded content is present`() {
        assertTrue("Expected at least 60 topics", Handbook.allTopics.size >= 60)
        assertNotNull(Handbook.topicById("ecg-basics"))
        assertNotNull(Handbook.topicById("abg-interpretation"))
        assertNotNull(Handbook.topicById("breaking-bad-news"))
        assertNotNull(Handbook.topicById("emergency-drug-doses"))
        assertNotNull(Handbook.topicById("lab-results"))
        assertNotNull(Handbook.topicById("clinical-etiquette"))
    }

    @Test
    fun `search covers new topics`() {
        assertTrue(Handbook.search("SPIKES").any { it.id == "breaking-bad-news" })
        assertTrue(Handbook.search("anion gap").isNotEmpty())
        assertTrue(Handbook.search("SBAR").isNotEmpty())
    }
}
