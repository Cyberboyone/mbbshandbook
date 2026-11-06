package com.mbbshandbook.app.ads

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.VideoOptions
import com.google.android.gms.ads.nativead.MediaView
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdOptions
import com.google.android.gms.ads.nativead.NativeAdView
import com.mbbshandbook.app.BuildConfig
import com.mbbshandbook.app.R

private const val NATIVE_TAG = "HandbookAds"

/**
 * A separate Native Advanced slot, not a replacement for any banner. The real
 * SDK assets are registered inside NativeAdView; no preview/fake creative or
 * custom ad click handler is used. Unfilled release slots emit no layout.
 */
@Composable
fun NativeAdvancedAd(modifier: Modifier = Modifier, placement: String) {
    if (LocalInspectionMode.current) return
    val context = LocalContext.current
    val activity = remember(context) { context.findNativeAdActivity() }

    if (!AdsController.adsReady) {
        if (BuildConfig.DEBUG) {
            NativeAdDebugStatus(
                modifier = modifier,
                message = "Native Advanced: ${AdsController.statusMessage}",
                retryEnabled = AdsController.canRetry
            ) { activity?.let { AdsController.retry(it) } }
        }
        return
    }

    var nativeAd by remember(context, placement) { mutableStateOf<NativeAd?>(null) }
    var loadError by remember(context, placement) { mutableStateOf<String?>(null) }
    var isLoading by remember(context, placement) { mutableStateOf(false) }
    var attempt by remember(context, placement) { mutableIntStateOf(0) }
    val generation = AdsController.requestGeneration

    // One request per placement/visit. Recomposition does not reload; an explicit
    // retry or a new consent generation does. Never overlap requests on a loader.
    DisposableEffect(context, placement, generation, attempt) {
        var disposed = false
        var receivedAd: NativeAd? = null
        nativeAd = null
        loadError = null
        isLoading = true

        if (AdsController.adsReady) {
            val loader = AdLoader.Builder(
                context.applicationContext,
                AdsConfig.NATIVE_ADVANCED_AD_UNIT_ID
            )
                .forNativeAd { ad ->
                    // A request can finish after navigation or a consent change.
                    if (disposed || !AdsController.adsReady || activity?.isDestroyed == true ||
                        activity?.isFinishing == true || activity?.isChangingConfigurations == true
                    ) {
                        ad.destroy()
                    } else {
                        receivedAd?.destroy()
                        receivedAd = ad
                        nativeAd = ad
                        isLoading = false
                        Log.i(NATIVE_TAG, "Native Advanced ad loaded ($placement)")
                    }
                }
                .withAdListener(object : AdListener() {
                    override fun onAdFailedToLoad(error: LoadAdError) {
                        if (!disposed) {
                            isLoading = false
                            loadError = "Load failed (${error.code}): ${error.message}"
                            Log.w(
                                NATIVE_TAG,
                                "Native Advanced failed ($placement): ${error.code} ${error.message}"
                            )
                        }
                    }

                    override fun onAdImpression() {
                        Log.i(NATIVE_TAG, "Native Advanced impression ($placement)")
                    }

                    override fun onAdClicked() {
                        Log.i(NATIVE_TAG, "Native Advanced clicked ($placement)")
                    }
                })
                .withNativeAdOptions(
                    NativeAdOptions.Builder()
                        .setAdChoicesPlacement(NativeAdOptions.ADCHOICES_TOP_RIGHT)
                        .setVideoOptions(VideoOptions.Builder().setStartMuted(true).build())
                        .build()
                )
                .build()
            loader.loadAd(AdRequest.Builder().build())
        }

        onDispose {
            disposed = true
            receivedAd?.destroy()
            receivedAd = null
        }
    }

    val ad = nativeAd
    when {
        ad != null -> Column {
            Spacer(Modifier.height(10.dp))
            NativeAdvancedCard(ad, modifier)
        }
        BuildConfig.DEBUG -> NativeAdDebugStatus(
            modifier = modifier,
            message = "Native Advanced: ${loadError ?: if (isLoading) "Loading…" else "Waiting…"}",
            retryEnabled = !isLoading
        ) {
            // A no-fill retry is local; only an unavailable consent gate needs
            // to restart the shared controller (and bump its generation).
            attempt++
        }
    }
}

@Composable
private fun NativeAdvancedCard(nativeAd: NativeAd, modifier: Modifier = Modifier) {
    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner, nativeAd) {
        val video = nativeAd.mediaContent?.videoController
        val observer = LifecycleEventObserver { _, event ->
            // Do not restart an ended video or override the user's play choice.
            if (event == Lifecycle.Event.ON_PAUSE) video?.pause()
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        if (!lifecycleOwner.lifecycle.currentState.isAtLeast(Lifecycle.State.RESUMED)) {
            // A late fill must not start background playback after ON_PAUSE.
            video?.pause()
        }
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
            video?.pause()
        }
    }

    AndroidView(
        modifier = modifier.fillMaxWidth(),
        factory = { context ->
            (LayoutInflater.from(context).inflate(R.layout.native_advanced_ad, null) as NativeAdView)
                .also { view -> bindNativeAd(view, nativeAd) }
        },
        update = { view ->
            // Avoid registering the same ad again on unrelated recompositions.
            if (view.getTag(R.id.native_root) != nativeAd) bindNativeAd(view, nativeAd)
        }
    )
}

/** Register every asset before handing the populated view to the SDK. */
private fun bindNativeAd(view: NativeAdView, ad: NativeAd) {
    val context = view.context
    val headline = view.findViewById<TextView>(R.id.native_headline)
    val icon = view.findViewById<ImageView>(R.id.native_icon)
    val media = view.findViewById<MediaView>(R.id.native_media)
    val body = view.findViewById<TextView>(R.id.native_body)
    val advertiser = view.findViewById<TextView>(R.id.native_advertiser)
    val stars = view.findViewById<RatingBar>(R.id.native_stars)
    val price = view.findViewById<TextView>(R.id.native_price)
    val store = view.findViewById<TextView>(R.id.native_store)
    val callToAction = view.findViewById<TextView>(R.id.native_call_to_action)
    val footer = view.findViewById<View>(R.id.native_footer)
    val metadata = view.findViewById<View>(R.id.native_metadata)

    val regular = ResourcesCompat.getFont(context, R.font.poppins_regular)
    val semibold = ResourcesCompat.getFont(context, R.font.poppins_semibold)
    listOf(headline, body, advertiser, price, store, callToAction).forEach { it.typeface = regular }
    headline.typeface = semibold
    callToAction.typeface = semibold

    view.mediaView = media
    view.headlineView = headline
    view.iconView = icon
    view.bodyView = body
    view.advertiserView = advertiser
    view.starRatingView = stars
    view.priceView = price
    view.storeView = store
    view.callToActionView = callToAction

    headline.text = ad.headline
    media.setImageScaleType(ImageView.ScaleType.CENTER_CROP)
    ad.mediaContent?.let {
        media.mediaContent = it
        media.visibility = View.VISIBLE
    } ?: run { media.visibility = View.GONE }

    icon.setImageDrawable(ad.icon?.drawable)
    icon.visibility = if (ad.icon != null) View.VISIBLE else View.GONE
    bindText(body, ad.body)
    bindText(advertiser, ad.advertiser)
    bindText(price, ad.price)
    bindText(store, ad.store)
    bindText(callToAction, ad.callToAction)
    stars.visibility = if (ad.starRating != null) View.VISIBLE else View.GONE
    ad.starRating?.let { stars.rating = it.toFloat() }
    metadata.visibility = if (ad.advertiser != null || ad.starRating != null) View.VISIBLE else View.GONE
    footer.visibility = if (ad.price != null || ad.store != null || ad.callToAction != null) {
        View.VISIBLE
    } else {
        View.GONE
    }

    // The TextView CTA deliberately has no onClickListener: the SDK handles it.
    view.setNativeAd(ad)
    view.setTag(R.id.native_root, ad)
}

private fun bindText(view: TextView, value: String?) {
    view.text = value.orEmpty()
    view.visibility = if (value.isNullOrEmpty()) View.GONE else View.VISIBLE
}

@Composable
private fun NativeAdDebugStatus(
    modifier: Modifier,
    message: String,
    retryEnabled: Boolean,
    onRetry: () -> Unit
) {
    Column(modifier.fillMaxWidth()) {
        Spacer(Modifier.height(10.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .padding(horizontal = 12.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                message,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1f)
            )
            TextButton(onClick = onRetry, enabled = retryEnabled) { Text("Retry") }
        }
    }
}

private tailrec fun Context.findNativeAdActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findNativeAdActivity()
    else -> null
}
