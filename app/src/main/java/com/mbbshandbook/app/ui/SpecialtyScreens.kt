package com.mbbshandbook.app.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mbbshandbook.app.data.RoadmapStage
import com.mbbshandbook.app.data.Specialties
import com.mbbshandbook.app.data.Specialty

/** Gradient accent per specialty group. */
private fun specialtyGradient(category: String): List<Color> = when (category) {
    "Medical & longitudinal" -> listOf(Color(0xFF00695C), Color(0xFF26A69A))
    "Surgical & procedural" -> listOf(Color(0xFFC62828), Color(0xFFEF5350))
    "Acute & diagnostic" -> listOf(Color(0xFFE65100), Color(0xFFFFA726))
    else -> listOf(Color(0xFF4527A0), Color(0xFF7E57C2))
}

// ---------------------------------------------------------------------------
// Catalog
// ---------------------------------------------------------------------------

@Composable
fun SpecialtiesScreen(onSpecialtyClick: (String) -> Unit, onBack: () -> Unit) {
    var query by remember { mutableStateOf("") }
    val results = remember(query) { Specialties.search(query) }
    val grouped = remember(results) { results.groupBy { it.category } }

    ToolScaffold("Specialties & Roadmaps", onBack, showTestAd = true) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp)
        ) {
            OutlinedTextField(
                value = query,
                onValueChange = { query = it },
                placeholder = { Text("Search ${Specialties.all.size} specialties…") },
                leadingIcon = { Icon(Icons.Filled.Search, null) },
                singleLine = true,
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(10.dp))
            Text(
                "What the work is really like, training roadmaps, exams, lifestyle and how to start now. Paths vary by country - confirm with your local college.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(Modifier.height(12.dp))
            if (results.isEmpty()) {
                Text(
                    "No specialties match \"$query\".",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    contentPadding = PaddingValues(bottom = 24.dp)
                ) {
                    grouped.forEach { (category, list) ->
                        item(key = "header-$category") {
                            Text(
                                category,
                                style = MaterialTheme.typography.titleMedium,
                                color = specialtyGradient(category).first(),
                                modifier = Modifier.padding(top = 6.dp, bottom = 2.dp)
                            )
                        }
                        items(list, key = { it.id }) { specialty ->
                            SpecialtyCard(specialty, onClick = { onSpecialtyClick(specialty.id) })
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SpecialtyCard(specialty: Specialty, onClick: () -> Unit) {
    val accent = specialtyGradient(specialty.category)
    Card(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            Modifier
                .background(
                    Brush.linearGradient(
                        listOf(
                            MaterialTheme.colorScheme.surface,
                            accent.last().copy(alpha = 0.18f)
                        )
                    )
                )
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(46.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Brush.linearGradient(accent)),
                contentAlignment = Alignment.Center
            ) {
                Text(specialty.emoji, fontSize = 24.sp)
            }
            Spacer(Modifier.width(12.dp))
            Column(Modifier.weight(1f)) {
                Text(specialty.name, style = MaterialTheme.typography.titleSmall)
                Text(
                    specialty.tagline,
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
}

// ---------------------------------------------------------------------------
// Detail
// ---------------------------------------------------------------------------

@Composable
fun SpecialtyDetailScreen(specialtyId: String, onBack: () -> Unit) {
    val specialty = Specialties.byId(specialtyId)
    if (specialty == null) {
        ToolScaffold("Specialty", onBack) { padding ->
            Box(Modifier.fillMaxSize().padding(padding), contentAlignment = Alignment.Center) {
                Text("Specialty not found", color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
        return
    }
    val accent = specialtyGradient(specialty.category)

    ToolScaffold(specialty.name, onBack) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // ---- Header ----
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(20.dp))
                        .background(Brush.linearGradient(accent))
                        .padding(20.dp)
                ) {
                    Column {
                        Text(specialty.emoji, fontSize = 40.sp)
                        Spacer(Modifier.height(8.dp))
                        Text(
                            specialty.name,
                            style = MaterialTheme.typography.headlineSmall,
                            color = Color.White
                        )
                        Text(
                            specialty.tagline,
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.White.copy(alpha = 0.9f)
                        )
                        Spacer(Modifier.height(10.dp))
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(50))
                                .background(Color.White.copy(alpha = 0.22f))
                                .padding(horizontal = 12.dp, vertical = 6.dp)
                        ) {
                            Text(
                                "⏱ ${specialty.trainingLength}",
                                style = MaterialTheme.typography.labelSmall,
                                color = Color.White
                            )
                        }
                    }
                }
            }

            // ---- Overview ----
            item { DetailCard("What the work is", accent) {
                Text(specialty.overview, style = MaterialTheme.typography.bodyMedium)
            } }

            // ---- Day in the life ----
            item { DetailCard("A day in the life", accent) {
                BulletList(specialty.dayInLife)
            } }

            // ---- Roadmap ----
            item {
                Text(
                    "🗺 Training roadmap",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
            item {
                Card(
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Box(Modifier.padding(16.dp)) {
                        RoadmapTimeline(specialty.roadmap, accent)
                    }
                }
            }

            // ---- Skills ----
            item { DetailCard("Key skills", accent) {
                BulletList(specialty.keySkills)
            } }

            // ---- Rewards / challenges ----
            item { DetailCard("✅ What makes it great", accent) {
                BulletList(specialty.rewards)
            } }
            item { DetailCard("⚠️ The challenges", accent) {
                BulletList(specialty.challenges)
            } }

            // ---- Lifestyle ----
            item { DetailCard("Lifestyle & competitiveness", accent) {
                Text(specialty.lifestyle, style = MaterialTheme.typography.bodyMedium)
            } }

            // ---- Exams ----
            item { DetailCard("🎓 Exams & qualifications", accent) {
                BulletList(specialty.exams)
            } }

            // ---- Start now ----
            item { DetailCard("🚀 Start now in med school", accent) {
                BulletList(specialty.startNow)
            } }

            // ---- Disclaimer ----
            item {
                Text(
                    "Training structures, exam names and durations vary by country and change over time. This is educational career guidance - always confirm current requirements with your national postgraduate medical college or regulator.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(horizontal = 4.dp)
                )
            }
        }
    }
}

@Composable
private fun DetailCard(title: String, accent: List<Color>, content: @Composable () -> Unit) {
    Card(
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(Modifier.padding(16.dp)) {
            Text(
                title,
                style = MaterialTheme.typography.titleMedium,
                color = accent.first()
            )
            Spacer(Modifier.height(8.dp))
            content()
        }
    }
}

@Composable
private fun BulletList(items: List<String>) {
    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        items.forEach { item ->
            Row {
                Text(
                    "•",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(item, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

/** Vertical timeline: numbered gradient nodes joined by a line, stage details beside. */
@Composable
private fun RoadmapTimeline(stages: List<RoadmapStage>, accent: List<Color>) {
    Column {
        stages.forEachIndexed { index, stage ->
            Row(modifier = Modifier.height(IntrinsicSize.Min)) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.width(34.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(30.dp)
                            .clip(CircleShape)
                            .background(Brush.linearGradient(accent)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "${index + 1}",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                    if (index < stages.lastIndex) {
                        Box(
                            modifier = Modifier
                                .width(2.dp)
                                .fillMaxHeight()
                                .background(accent.first().copy(alpha = 0.3f))
                        )
                    }
                }
                Spacer(Modifier.width(12.dp))
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(bottom = if (index < stages.lastIndex) 18.dp else 0.dp)
                ) {
                    Text(
                        stage.title,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        stage.duration,
                        style = MaterialTheme.typography.labelMedium,
                        color = accent.first()
                    )
                    Spacer(Modifier.height(6.dp))
                    stage.details.forEach { detail ->
                        Row(Modifier.padding(bottom = 4.dp)) {
                            Text(
                                "–",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.padding(end = 6.dp)
                            )
                            Text(
                                detail,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }
        }
    }
}
