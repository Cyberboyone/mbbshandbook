package com.mbbshandbook.app.ads

import android.app.Activity
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

/** Visible only when UMP says a privacy-options entry point is required. */
@Composable
fun AdPrivacyOptionsButton(modifier: Modifier = Modifier) {
    if (!AdsController.privacyOptionsRequired) return
    val activity = LocalContext.current as? Activity ?: return
    TextButton(
        onClick = { AdsController.showPrivacyOptions(activity) },
        modifier = modifier
    ) {
        Text("Ad privacy choices")
    }
}
