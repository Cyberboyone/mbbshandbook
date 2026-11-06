package com.mbbshandbook.app.ui

import androidx.compose.ui.graphics.Color

/** Per-section gradient accent used on cards and chips (dark fintech family). */
fun sectionGradient(sectionId: String): List<Color> = when (sectionId) {
    "start" -> listOf(Color(0xFF14584A), Color(0xFF0C2620))
    "preclinical" -> listOf(Color(0xFF14406B), Color(0xFF0C1F33))
    "paraclinical" -> listOf(Color(0xFF45215E), Color(0xFF20102E))
    "clinical" -> listOf(Color(0xFF0F4A42), Color(0xFF0A2622))
    "skills" -> listOf(Color(0xFF6B2430), Color(0xFF331116))
    "exams" -> listOf(Color(0xFF6B3A12), Color(0xFF331B0A))
    "ethics" -> listOf(Color(0xFF2E414C), Color(0xFF152026))
    "wellbeing" -> listOf(Color(0xFF2A5426), Color(0xFF142912))
    "resources" -> listOf(Color(0xFF352465), Color(0xFF191131))
    "mnemonics" -> listOf(Color(0xFF5E1F44), Color(0xFF2D0F21))
    "emergencies" -> listOf(Color(0xFF6B1F1F), Color(0xFF330F0F))
    else -> listOf(Color(0xFF14584A), Color(0xFF0C2620))
}

val heroGradient: List<Color> = listOf(Color(0xFF1F5244), Color(0xFF12352C), Color(0xFF0C221C))
