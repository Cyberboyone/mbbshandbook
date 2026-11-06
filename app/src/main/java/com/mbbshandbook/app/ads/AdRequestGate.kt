package com.mbbshandbook.app.ads

/**
 * Keeps SDK initialization separate from permission to request an ad.
 *
 * UMP can change whether ads are allowed independently of SDK initialization.
 * A completed initialization must never override a later negative consent check.
 * The SDK itself only needs to be initialized once per process.
 */
class AdRequestGate {
    var isInitializing: Boolean = false
        private set

    var isInitialized: Boolean = false
        private set

    /** Returns true only for the caller that should initialize the SDK. */
    @Synchronized
    fun beginInitialization(canRequestAds: Boolean): Boolean {
        if (!canRequestAds || isInitializing || isInitialized) return false
        isInitializing = true
        return true
    }

    @Synchronized
    fun completeInitialization() {
        isInitializing = false
        isInitialized = true
    }

    @Synchronized
    fun canLoadAds(canRequestAds: Boolean): Boolean = isInitialized && canRequestAds
}
