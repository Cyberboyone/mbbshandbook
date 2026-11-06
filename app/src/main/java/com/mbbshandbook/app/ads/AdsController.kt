package com.mbbshandbook.app.ads

import android.app.Activity
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.google.android.gms.ads.MobileAds
import com.google.android.ump.ConsentInformation
import com.google.android.ump.ConsentRequestParameters
import com.google.android.ump.UserMessagingPlatform

/**
 * Consent-first startup for Google's sample/test banners.
 *
 * Cached permission is checked while consent information updates. Failures are
 * observable and retryable rather than permanently disabling ads in this
 * process. No debug geography, automatic consent reset, or consent bypass is
 * enabled, including in debug builds.
 */
object AdsController {
    private const val TAG = "HandbookAds"

    /** SDK initialization alone never grants permission to request an ad. */
    val adsReady: Boolean
        get() = sdkInitialized && canRequestAds

    var canRequestAds by mutableStateOf(false)
        private set

    var privacyOptionsRequired by mutableStateOf(false)
        private set

    var statusMessage by mutableStateOf("Checking ad privacy…")
        private set

    var canRetry by mutableStateOf(false)
        private set

    /** Explicit retries get a new key without reloading on diagnostic updates. */
    var requestGeneration by mutableIntStateOf(0)
        private set

    private var sdkInitialized by mutableStateOf(false)
    private val requestGate = AdRequestGate()
    private val mainHandler = Handler(Looper.getMainLooper())
    private var consentUpdateInFlight = false
    private var consentFormInFlight = false
    private var privacyFormInFlight = false

    fun start(activity: Activity) {
        mainHandler.post {
            if (!isActive(activity)) return@post
            if (consentUpdateInFlight || consentFormInFlight || privacyFormInFlight) return@post

            val consentInformation = UserMessagingPlatform.getConsentInformation(activity)
            consentUpdateInFlight = true
            canRetry = false
            statusMessage = "Checking ad privacy…"

            consentInformation.requestConsentInfoUpdate(
                activity,
                ConsentRequestParameters.Builder().build(),
                {
                    consentUpdateInFlight = false
                    if (isActive(activity)) {
                        refresh(activity)
                        gatherConsent(activity)
                    }
                },
                { error ->
                    consentUpdateInFlight = false
                    Log.w(TAG, "Consent update failed (${error.errorCode}): ${error.message}")
                    if (isActive(activity)) {
                        // Respect cached permission, but do not load a form with
                        // unknown requirements after an update failure.
                        refresh(activity)
                        if (!canRequestAds) {
                            statusMessage = "Privacy check failed (${error.errorCode}): ${error.message}"
                            canRetry = true
                        }
                    }
                }
            )

            // UMP permits using the previous session's decision while this
            // request runs. This check is deduplicated by AdRequestGate.
            refresh(activity)
        }
    }

    fun retry(activity: Activity) {
        requestGeneration++
        start(activity)
    }

    fun showPrivacyOptions(activity: Activity) {
        mainHandler.post {
            if (!isActive(activity) || consentFormInFlight || privacyFormInFlight) return@post
            privacyFormInFlight = true
            UserMessagingPlatform.showPrivacyOptionsForm(activity) { error ->
                privacyFormInFlight = false
                if (error != null) {
                    Log.w(TAG, "Privacy options failed (${error.errorCode}): ${error.message}")
                }
                if (isActive(activity)) {
                    refresh(activity)
                    if (error != null && !canRequestAds) {
                        statusMessage = "Privacy options failed (${error.errorCode}): ${error.message}"
                        canRetry = true
                    }
                }
            }
        }
    }

    private fun gatherConsent(activity: Activity) {
        if (!isActive(activity) || consentFormInFlight) return
        consentFormInFlight = true
        canRetry = false
        statusMessage = "Checking whether privacy choices are needed…"
        UserMessagingPlatform.loadAndShowConsentFormIfRequired(activity) { error ->
            consentFormInFlight = false
            if (error != null) {
                Log.w(TAG, "Consent form failed (${error.errorCode}): ${error.message}")
            }
            if (isActive(activity)) {
                refresh(activity)
                if (!canRequestAds) {
                    statusMessage = if (error != null) {
                        "Privacy form failed (${error.errorCode}): ${error.message}"
                    } else {
                        "Ads are paused by your privacy choices or privacy settings."
                    }
                    canRetry = true
                }
            }
        }
        // Also checks permission when UMP determines no form is required.
        refresh(activity)
    }

    private fun refresh(activity: Activity) {
        val consentInformation = UserMessagingPlatform.getConsentInformation(activity)
        privacyOptionsRequired =
            consentInformation.privacyOptionsRequirementStatus ==
                ConsentInformation.PrivacyOptionsRequirementStatus.REQUIRED
        canRequestAds = consentInformation.canRequestAds()

        if (canRequestAds) {
            canRetry = false
            initialize(activity.applicationContext)
        } else if (!consentUpdateInFlight && !consentFormInFlight && !privacyFormInFlight) {
            statusMessage = "Ads are paused until the privacy check allows requests."
            canRetry = true
        }
    }

    private fun initialize(context: Context) {
        if (requestGate.isInitialized) {
            sdkInitialized = true
            if (canRequestAds) statusMessage = "Test ads ready"
            return
        }
        if (!requestGate.beginInitialization(canRequestAds)) return
        statusMessage = "Initializing Google test ads…"
        MobileAds.initialize(context) {
            mainHandler.post {
                requestGate.completeInitialization()
                sdkInitialized = true
                if (canRequestAds) {
                    statusMessage = "Test ads ready"
                    Log.i(TAG, "Google Mobile Ads initialized; test banner requests enabled")
                }
            }
        }
    }

    private fun isActive(activity: Activity): Boolean = !activity.isFinishing && !activity.isDestroyed
}
