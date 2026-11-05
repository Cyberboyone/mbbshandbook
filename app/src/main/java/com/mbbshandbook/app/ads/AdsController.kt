package com.mbbshandbook.app.ads

import android.app.Activity
import android.os.Handler
import android.os.Looper
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.google.android.gms.ads.MobileAds
import com.google.android.ump.ConsentInformation
import com.google.android.ump.ConsentRequestParameters
import com.google.android.ump.UserMessagingPlatform
import java.util.concurrent.atomic.AtomicBoolean

/**
 * Consent-first gate for test ads.
 *
 * The banner is only composed after [adsReady] becomes true. UMP consent is
 * refreshed on each app launch; if consent is unavailable or revoked, ads stay
 * hidden and the handbook remains fully usable.
 */
object AdsController {
    var adsReady by mutableStateOf(false)
        private set

    var privacyOptionsRequired by mutableStateOf(false)
        private set

    private var started = false
    private val initialized = AtomicBoolean(false)
    private val mainHandler = Handler(Looper.getMainLooper())

    fun start(activity: Activity) {
        if (started) return
        started = true

        val consentInformation = UserMessagingPlatform.getConsentInformation(activity)
        val params = ConsentRequestParameters.Builder().build()

        consentInformation.requestConsentInfoUpdate(
            activity,
            params,
            {
                refresh(activity)
                gatherConsent(activity)
            },
            {
                // If the update fails, UMP may still have a previous consent
                // decision. Respect canRequestAds() either way.
                refresh(activity)
                gatherConsent(activity)
            }
        )
    }

    fun showPrivacyOptions(activity: Activity) {
        UserMessagingPlatform.showPrivacyOptionsForm(activity) {
            refresh(activity)
        }
    }

    private fun gatherConsent(activity: Activity) {
        UserMessagingPlatform.loadAndShowConsentFormIfRequired(activity) {
            refresh(activity)
        }
    }

    private fun refresh(activity: Activity) {
        val consentInformation = UserMessagingPlatform.getConsentInformation(activity)
        privacyOptionsRequired =
            consentInformation.privacyOptionsRequirementStatus ==
                ConsentInformation.PrivacyOptionsRequirementStatus.REQUIRED

        if (consentInformation.canRequestAds()) {
            initialize(activity.applicationContext)
        } else {
            adsReady = false
        }
    }

    private fun initialize(context: android.content.Context) {
        if (initialized.getAndSet(true)) {
            adsReady = true
            return
        }
        MobileAds.initialize(context) {
            mainHandler.post { adsReady = true }
        }
    }
}
