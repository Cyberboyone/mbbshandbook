package com.mbbshandbook.app.ads

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.layout.layout
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.mbbshandbook.app.BuildConfig

private const val TAG = "HandbookAds"

/**
 * Real AdMob banners using the publisher-supplied unit; no placeholder
 * creative or consent bypass.
 *
 * The AdView must be attached and measured BEFORE loading. Its outer slot stays
 * collapsed until fill, while the Android view inside it retains a real,
 * non-zero measurement. Debug bottom slots expose startup/load errors + retry;
 * release slots stay quiet on failure. Inline slots never show diagnostic UI.
 */
@Composable
fun AdBanner(modifier: Modifier = Modifier, isInline: Boolean = false) {
    if (LocalInspectionMode.current) return

    val context = LocalContext.current
    val placement = if (isInline) "inline" else "bottom"
    val activity = remember(context) { context.findActivity() }

    if (!AdsController.adsReady) {
        if (BuildConfig.DEBUG && !isInline) {
            AdDebugStatus(
                modifier = modifier,
                message = "Ads: ${AdsController.statusMessage}",
                retryEnabled = AdsController.canRetry
            ) {
                activity?.let { AdsController.retry(it) }
            }
        }
        return
    }

    val configuration = LocalConfiguration.current
    val adWidthDp = if (isInline) {
        (configuration.screenWidthDp - 32).coerceAtLeast(1)
    } else {
        configuration.screenWidthDp.coerceAtLeast(1)
    }
    val adSize = remember(context, adWidthDp, isInline, configuration.orientation) {
        if (isInline) {
            AdSize.getInlineAdaptiveBannerAdSize(adWidthDp, AdsConfig.INLINE_AD_MAX_HEIGHT_DP)
        } else {
            AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(context, adWidthDp)
        }
    }

    var adLoaded by remember(context, adSize) { mutableStateOf(false) }
    var measured by remember(context, adSize) { mutableStateOf(false) }
    var loadError by remember(context, adSize) { mutableStateOf<String?>(null) }
    var localRetry by remember(context, adSize) { mutableIntStateOf(0) }
    val generation = AdsController.requestGeneration
    val requestGate = remember(context, adSize) { BannerRequestGate() }
    val adView = remember(context, adSize) {
        AdView(context).apply {
            adUnitId = AdsConfig.BANNER_AD_UNIT_ID
            setAdSize(adSize)
            // Keep the real SDK view visible to its own lifecycle. The Compose
            // wrapper clips it while loading; do not use View.GONE/INVISIBLE.
            adListener = object : AdListener() {
                override fun onAdLoaded() {
                    adLoaded = true
                    loadError = null
                    Log.i(TAG, "AdMob banner loaded ($placement, ${adWidthDp}dp)")
                    requestLayout()
                }

                override fun onAdFailedToLoad(error: LoadAdError) {
                    adLoaded = false
                    loadError = "Load failed (${error.code}): ${error.message}"
                    Log.w(TAG, "AdMob banner failed ($placement): ${error.code} ${error.message}")
                }
            }
        }
    }

    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner, adView) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_RESUME -> adView.resume()
                Lifecycle.Event.ON_PAUSE -> adView.pause()
                else -> Unit
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        if (lifecycleOwner.lifecycle.currentState.isAtLeast(Lifecycle.State.RESUMED)) {
            adView.resume()
        }
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
            adView.pause()
            adView.destroy()
        }
    }

    // Wait for AndroidView's factory/layout, not just composition. Loading a
    // detached or zero-sized adaptive view can fail or produce a blank banner.
    LaunchedEffect(adView, measured, generation, localRetry) {
        if (!measured || !AdsController.adsReady) return@LaunchedEffect
        withFrameNanos { }
        val allowed = requestGate.beginRequest(
            generation = generation,
            attempt = localRetry,
            canRequestAds = AdsController.adsReady,
            isAttached = adView.isAttachedToWindow,
            widthPixels = adView.width,
            heightPixels = adView.height
        )
        if (allowed) {
            loadError = null
            Log.i(TAG, "Requesting AdMob banner ($placement, ${adWidthDp}dp)")
            adView.loadAd(AdRequest.Builder().build())
        }
    }

    Column(modifier = modifier.fillMaxWidth()) {
        // List wrappers have no arrangement gap: an unfilled inline slot must
        // occupy exactly zero pixels, including its separator.
        if (isInline && adLoaded) Spacer(Modifier.height(10.dp))
        if (BuildConfig.DEBUG && !isInline && !adLoaded) {
            AdDebugStatus(message = "Ads: ${loadError ?: "Loading banner…"}") {
                localRetry++
                activity?.let { AdsController.retry(it) }
            }
        }
        if (adLoaded) {
            Text(
                text = "Advertisement",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.surface)
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 2.dp)
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface)
                .collapsedUntilVisible(adLoaded)
        ) {
            AndroidView(
                factory = { adView },
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    // Inline AdSize.getHeight() is zero until fill. Give the
                    // attached SDK view a real measurement while its outer
                    // slot is collapsed, then use the returned creative height.
                    .then(
                        if (!adLoaded && isInline) {
                            Modifier.requiredHeight(AdsConfig.INLINE_AD_MAX_HEIGHT_DP.dp)
                        } else {
                            Modifier
                        }
                    )
                    .onGloballyPositioned { coordinates ->
                        measured = coordinates.size.width > 0 && coordinates.size.height > 0
                    },
                update = { it.requestLayout() }
            )
        }
    }
}

/** Measure/attach the child normally, but report no occupied height until fill. */
private fun Modifier.collapsedUntilVisible(visible: Boolean): Modifier =
    clipToBounds().layout { measurable, constraints ->
        val child = measurable.measure(constraints.copy(minHeight = 0))
        layout(child.width, if (visible) child.height else 0) {
            // Placing keeps the Android view attached even in the collapsed slot.
            child.placeRelative(0, 0)
        }
    }

@Composable
private fun AdDebugStatus(
    modifier: Modifier = Modifier,
    message: String,
    retryEnabled: Boolean = true,
    onRetry: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(horizontal = 12.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f)
        )
        TextButton(onClick = onRetry, enabled = retryEnabled) { Text("Retry") }
    }
}

private tailrec fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}
