package com.mbbshandbook.app

import android.content.Context
import androidx.compose.runtime.mutableStateSetOf

/** Tiny persistent bookmark store backed by SharedPreferences. */
object Bookmarks {
    private const val PREFS = "bookmarks"
    private const val KEY = "topic_ids"

    val ids = mutableStateSetOf<String>()
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
