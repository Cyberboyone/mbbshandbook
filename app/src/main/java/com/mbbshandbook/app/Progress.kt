package com.mbbshandbook.app

import android.content.Context
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateSetOf
import com.mbbshandbook.app.data.Handbook

/** Tracks which topics have been read; persisted in SharedPreferences. */
object Progress {
    private const val PREFS = "progress"
    private const val KEY = "read_ids"

    val readIds = mutableStateSetOf<String>()
    private var prefs: android.content.SharedPreferences? = null

    fun init(context: Context) {
        val p = context.applicationContext.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
        prefs = p
        readIds.clear()
        readIds.addAll(p.getStringSet(KEY, emptySet()) ?: emptySet())
    }

    fun markRead(topicId: String) {
        if (readIds.add(topicId)) {
            prefs?.edit()?.putStringSet(KEY, readIds.toSet())?.apply()
        }
    }

    fun isRead(topicId: String): Boolean = readIds.contains(topicId)

    fun reset() {
        readIds.clear()
        prefs?.edit()?.remove(KEY)?.apply()
    }

    fun percent(): Int {
        val total = Handbook.allTopics.size
        if (total == 0) return 0
        val valid = readIds.count { id -> Handbook.allTopics.any { it.id == id } }
        return (valid * 100) / total
    }
}

/** Best quiz scores per category (percent), persisted. */
object QuizScores {
    private const val PREFS = "quiz_scores"

    val best = mutableStateMapOf<String, Int>()
    private var prefs: android.content.SharedPreferences? = null

    fun init(context: Context) {
        prefs = context.applicationContext.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
        best.clear()
        prefs?.all?.forEach { (k, v) -> (v as? Int)?.let { best[k] = it } }
    }

    fun record(categoryId: String, percent: Int) {
        val current = best[categoryId] ?: -1
        if (percent > current) {
            best[categoryId] = percent
            prefs?.edit()?.putInt(categoryId, percent)?.apply()
        }
    }
}
