package com.mbbshandbook.app

import android.content.Context
import androidx.compose.runtime.mutableStateListOf

/** Tiny persistent bookmark store backed by SharedPreferences. */
object Bookmarks {
    private const val PREFS = "bookmarks"
    private const val KEY = "topic_ids"

    // SnapshotStateList (observable). Kept duplicate-free by toggle()/init(),
    // so it behaves as a set; mutableStateSetOf needs Compose 1.8+.
    val ids = mutableStateListOf<String>()
    private var prefs: android.content.SharedPreferences? = null

    fun init(context: Context) {
        val p = context.applicationContext.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
        prefs = p
        ids.clear()
        ids.addAll(p.getStringSet(KEY, emptySet()) ?: emptySet())
    }

    fun toggle(topicId: String) {
        if (ids.contains(topicId)) ids.remove(topicId) else ids.add(topicId)
        prefs?.edit()?.putStringSet(KEY, ids.toSet())?.apply()
    }

    fun contains(topicId: String): Boolean = ids.contains(topicId)
}
