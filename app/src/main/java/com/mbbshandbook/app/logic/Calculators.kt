package com.mbbshandbook.app.logic

import kotlin.math.roundToInt

/**
 * Pure calculator logic, unit-tested on the JVM.
 * Educational reference implementations - always verify against local
 * clinical protocols before any real-world use.
 */
object Calculators {

    data class BmiResult(val bmi: Double, val category: String)

    fun bmi(weightKg: Double, heightCm: Double): BmiResult? {
        if (weightKg <= 0 || heightCm <= 0) return null
        val h = heightCm / 100.0
        val bmi = weightKg / (h * h)
        if (bmi !in 5.0..150.0) return null
        val category = when {
            bmi < 18.5 -> "Underweight"
            bmi < 25.0 -> "Healthy range"
            bmi < 30.0 -> "Overweight"
            bmi < 35.0 -> "Obesity class I"
            bmi < 40.0 -> "Obesity class II"
            else -> "Obesity class III"
        }
        return BmiResult((bmi * 10).roundToInt() / 10.0, category)
    }

    /** IV drip rate in drops/min. dropFactor = drops per mL of the giving set. */
    fun dripRate(volumeMl: Double, timeMinutes: Double, dropFactor: Int): Int? {
        if (volumeMl <= 0 || timeMinutes <= 0 || dropFactor <= 0) return null
        return ((volumeMl * dropFactor) / timeMinutes).roundToInt()
    }

    /** Infusion pump rate in mL/hour. */
    fun infusionRate(volumeMl: Double, timeMinutes: Double): Double? {
        if (volumeMl <= 0 || timeMinutes <= 0) return null
        return ((volumeMl / timeMinutes) * 60.0 * 10).roundToInt() / 10.0
    }

    /** Holliday-Segar 4-2-1 maintenance fluid rule, mL/hour (educational). */
    fun maintenanceFluidMlPerHr(weightKg: Double): Double? {
        if (weightKg <= 0 || weightKg > 300) return null
        val rate = when {
            weightKg <= 10 -> 4 * weightKg
            weightKg <= 20 -> 40 + 2 * (weightKg - 10)
            else -> 60 + 1 * (weightKg - 20)
        }
        return (rate * 10).roundToInt() / 10.0
    }

    data class GcsResult(val total: Int, val severity: String)

    fun gcs(eye: Int, verbal: Int, motor: Int): GcsResult? {
        if (eye !in 1..4 || verbal !in 1..5 || motor !in 1..6) return null
        val total = eye + verbal + motor
        val severity = when {
            total >= 13 -> "Mild impairment"
            total >= 9 -> "Moderate impairment"
            else -> "Severe impairment"
        }
        return GcsResult(total, severity)
    }

    data class ApgarResult(val total: Int, val interpretation: String)

    /** scores = Appearance, Pulse, Grimace, Activity, Respiration; each 0-2. */
    fun apgar(scores: List<Int>): ApgarResult? {
        if (scores.size != 5 || scores.any { it !in 0..2 }) return null
        val total = scores.sum()
        val interpretation = when {
            total >= 7 -> "Reassuring"
            total >= 4 -> "Moderately abnormal - support and reassess"
            else -> "Low - urgent resuscitation per protocol"
        }
        return ApgarResult(total, interpretation)
    }
}
