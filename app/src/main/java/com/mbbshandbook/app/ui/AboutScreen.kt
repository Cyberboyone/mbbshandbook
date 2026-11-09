package com.mbbshandbook.app.ui

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mbbshandbook.app.BuildConfig
import com.mbbshandbook.app.Legal
import com.mbbshandbook.app.ads.AdPrivacyOptionsButton

private fun openUrl(context: Context, url: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    try {
        context.startActivity(intent)
    } catch (e: ActivityNotFoundException) {
        Toast.makeText(context, "No app available to open this link.", Toast.LENGTH_SHORT).show()
    }
}

/**
 * About & privacy — reached from the home footer. Surfaces the Play-required
 * privacy policy, support contact, advertising disclosure and the educational
 * disclaimer, without any account or tracking beyond the ads SDK.
 */
@Composable
fun AboutScreen(onBack: () -> Unit) {
    val context = LocalContext.current

    ToolScaffold("About & privacy", onBack, showBannerAd = true) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            GlowPanel(modifier = Modifier.fillMaxWidth()) {
                Text(
                    "MBBS Handbook",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(Modifier.height(2.dp))
                Text(
                    "Version ${BuildConfig.VERSION_NAME} (build ${BuildConfig.VERSION_CODE})",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.White.copy(alpha = 0.75f)
                )
                Spacer(Modifier.height(10.dp))
                Text(
                    "An offline-first handbook, quiz bank and clinical reference for medical students.",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.White.copy(alpha = 0.88f)
                )
            }

            LegalCard("Your data stays on your device") {
                "Bookmarks, reading progress, quiz best scores and study-tool state are stored " +
                    "locally on this device. There is no account, no sign-in and no server of ours " +
                    "that receives your study data. Uninstalling the app removes it."
            }

            LegalCard("Advertising") {
                Legal.ADVERTISING_NOTICE
            }

            LegalCard("Educational use only") {
                Legal.DISCLAIMER
            }

            LinkRow(
                title = "Privacy policy",
                subtitle = "How ads and device data are handled",
                icon = { tint -> Icon(Icons.Filled.Lock, contentDescription = null, tint = tint, modifier = Modifier.size(18.dp)) }
            ) { openUrl(context, Legal.PRIVACY_POLICY_URL) }

            LinkRow(
                title = "Support & report a problem",
                subtitle = "Open the public issue tracker",
                icon = { tint -> Icon(Icons.Filled.Send, contentDescription = null, tint = tint, modifier = Modifier.size(18.dp)) }
            ) { openUrl(context, Legal.SUPPORT_URL) }

            AdPrivacyOptionsButton(modifier = Modifier.fillMaxWidth())

            Text(
                "Educational guidance for students — always follow your school's curriculum and local clinical guidelines.",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(top = 4.dp, bottom = 24.dp)
            )
        }
    }
}

@Composable
private fun LegalCard(title: String, body: () -> String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.surface)
            .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        Text(
            title,
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(Modifier.height(6.dp))
        Text(
            body(),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun LinkRow(
    title: String,
    subtitle: String,
    icon: @Composable (Color) -> Unit,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(MaterialTheme.colorScheme.surface)
            .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(14.dp))
            .clickable(onClick = onClick)
            .padding(14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        icon(MaterialTheme.colorScheme.primary)
        Spacer(Modifier.width(12.dp))
        Column(Modifier.weight(1f)) {
            Text(title, style = MaterialTheme.typography.titleSmall)
            Text(
                subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Icon(
            Icons.Filled.ChevronRight,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
