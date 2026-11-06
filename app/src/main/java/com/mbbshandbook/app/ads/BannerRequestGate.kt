package com.mbbshandbook.app.ads

/** Prevents duplicate loads while allowing an explicit new request after failure. */
class BannerRequestGate {
    private var lastRequest: Pair<Int, Int>? = null

    fun beginRequest(
        generation: Int,
        attempt: Int,
        canRequestAds: Boolean,
        isAttached: Boolean,
        widthPixels: Int,
        heightPixels: Int
    ): Boolean {
        val key = generation to attempt
        if (!canRequestAds || !isAttached || widthPixels <= 0 || heightPixels <= 0) return false
        if (lastRequest == key) return false
        lastRequest = key
        return true
    }
}
