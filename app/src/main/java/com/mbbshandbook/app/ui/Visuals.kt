package com.mbbshandbook.app.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mbbshandbook.app.R

/** Per-section banner art. */
@DrawableRes
fun sectionBanner(sectionId: String): Int = when (sectionId) {
    "start" -> R.drawable.hero
    "preclinical" -> R.drawable.science
    "paraclinical" -> R.drawable.science
    "clinical" -> R.drawable.hospital
    "skills" -> R.drawable.skills
    "exams" -> R.drawable.exams
    "ethics" -> R.drawable.balance
    "wellbeing" -> R.drawable.wellbeing
    "resources" -> R.drawable.hero
    "mnemonics" -> R.drawable.exams
    "emergencies" -> R.drawable.hospital
    else -> R.drawable.hero
}

/** Per-section gradient accent used on cards and chips. */
fun sectionGradient(sectionId: String): List<Color> = when (sectionId) {
    "start" -> listOf(Color(0xFF0B6E4F), Color(0xFF14A085))
    "preclinical" -> listOf(Color(0xFF1565C0), Color(0xFF42A5F5))
    "paraclinical" -> listOf(Color(0xFF6A1B9A), Color(0xFFAB47BC))
    "clinical" -> listOf(Color(0xFF00695C), Color(0xFF26A69A))
    "skills" -> listOf(Color(0xFFC62828), Color(0xFFEF5350))
    "exams" -> listOf(Color(0xFFE65100), Color(0xFFFFA726))
    "ethics" -> listOf(Color(0xFF37474F), Color(0xFF78909C))
    "wellbeing" -> listOf(Color(0xFF2E7D32), Color(0xFF81C784))
    "resources" -> listOf(Color(0xFF4527A0), Color(0xFF7E57C2))
    "mnemonics" -> listOf(Color(0xFFAD1457), Color(0xFFEC407A))
    "emergencies" -> listOf(Color(0xFFB71C1C), Color(0xFFFF5252))
    else -> listOf(Color(0xFF0B6E4F), Color(0xFF14A085))
}

val heroGradient: List<Color> = listOf(Color(0xFF062D21), Color(0xFF0B6E4F), Color(0xFF14A085))

/** Rounded banner image with a subtle dark gradient at the bottom for text overlay. */
@Composable
fun BannerImage(
    @DrawableRes res: Int,
    height: Dp,
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 20.dp,
    overlay: Boolean = true
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height)
            .clip(RoundedCornerShape(cornerRadius))
    ) {
        Image(
            painter = painterResource(res),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        if (overlay) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            0.45f to Color.Transparent,
                            1f to Color.Black.copy(alpha = 0.55f)
                        )
                    )
            )
        }
    }
}
