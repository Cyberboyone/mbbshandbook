package com.mbbshandbook.app.data

/** One stage of a specialty training roadmap. */
data class RoadmapStage(
    val title: String,
    val duration: String,
    val details: List<String>
)

/**
 * A career specialty with a broad, practical profile and training roadmap.
 * Durations are typical ranges; exact pathways vary by country and regulator.
 */
data class Specialty(
    val id: String,
    val name: String,
    val emoji: String,
    val category: String,
    val tagline: String,
    val trainingLength: String,
    val overview: String,
    val dayInLife: List<String>,
    val keySkills: List<String>,
    val rewards: List<String>,
    val challenges: List<String>,
    val lifestyle: String,
    val exams: List<String>,
    val roadmap: List<RoadmapStage>,
    val startNow: List<String>
)

/**
 * Specialty catalogue. Educational career guidance only - training structures,
 * exam names and durations differ between countries (e.g. Nigeria: housemanship,
 * NYSC, WACP/NPMCN residency; UK: Foundation, Royal College exams, specialty
 * training; US: USMLE, the Match, ACGME residency). Always confirm with your
 * local postgraduate medical college or regulator.
 */
object Specialties {

    val all: List<Specialty> =
        medicalSpecialties + proceduralSpecialties + acuteAndDiagnosticSpecialties

    val categories: List<String> = all.map { it.category }.distinct()

    fun byId(id: String): Specialty? = all.firstOrNull { it.id == id }

    fun search(query: String): List<Specialty> {
        val q = query.trim().lowercase()
        if (q.isEmpty()) return all
        return all.filter { s ->
            s.name.lowercase().contains(q) ||
                s.tagline.lowercase().contains(q) ||
                s.category.lowercase().contains(q) ||
                s.overview.lowercase().contains(q) ||
                s.keySkills.any { it.lowercase().contains(q) }
        }
    }
}
