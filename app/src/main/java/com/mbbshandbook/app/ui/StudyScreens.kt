package com.mbbshandbook.app.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Casino
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mbbshandbook.app.QuizScores
import com.mbbshandbook.app.data.FlashcardDeck
import com.mbbshandbook.app.data.GlossaryEntry
import com.mbbshandbook.app.data.QuizCategory
import com.mbbshandbook.app.data.StudyTools
import com.mbbshandbook.app.logic.Calculators
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random

// ---------------------------------------------------------------------------
// Shared scaffold
// ---------------------------------------------------------------------------

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ToolScaffold(
    title: String,
    onBack: () -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(title, style = MaterialTheme.typography.titleMedium) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        },
        containerColor = MaterialTheme.colorScheme.background,
        content = content
    )
}

// ---------------------------------------------------------------------------
// QUIZZES
// ---------------------------------------------------------------------------

@Composable
fun QuizHomeScreen(onCategoryClick: (String) -> Unit, onBack: () -> Unit) {
    ToolScaffold("Quizzes", onBack) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Text(
                    "Pick a category. ${StudyTools.quizCategories.sumOf { it.questions.size }} questions with explanations - 12 random questions per round, best scores saved.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            items(StudyTools.quizCategories, key = { it.id }) { cat ->
                QuizCategoryCard(cat, onClick = { onCategoryClick(cat.id) })
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun QuizCategoryCard(cat: QuizCategory, onClick: () -> Unit) {
    val best = QuizScores.best[cat.id]
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
                            MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f)
                        )
                    )
                )
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(cat.emoji, fontSize = 30.sp)
            Spacer(Modifier.width(14.dp))
            Column(Modifier.weight(1f)) {
                Text(cat.title, style = MaterialTheme.typography.titleSmall)
                Text(
                    "${cat.questions.size} questions",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            if (best != null) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(50))
                        .background(
                            if (best >= 70) MaterialTheme.colorScheme.primary
                            else MaterialTheme.colorScheme.tertiary
                        )
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Text("Best $best%", style = MaterialTheme.typography.labelSmall, color = Color.White)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizPlayScreen(categoryId: String, onBack: () -> Unit) {
    val category = StudyTools.quizCategories.firstOrNull { it.id == categoryId }
    if (category == null) {
        ToolScaffold("Quiz", onBack) { padding -> EmptyState("Quiz not found", padding) }
        return
    }

    var index by remember { mutableIntStateOf(0) }
    var selected by remember { mutableIntStateOf(-1) }
    var score by remember { mutableIntStateOf(0) }
    var finished by remember { mutableStateOf(false) }
    var round by remember { mutableIntStateOf(0) }

    val questions = remember(round) { category.questions.shuffled().take(12) }
    val q = questions[index]

    ToolScaffold("${category.emoji} ${category.title}", onBack) { padding ->
        if (finished) {
            val percent = (score * 100) / questions.size
            LaunchedEffect(Unit) { QuizScores.record(category.id, percent) }
            QuizResult(
                modifier = Modifier.padding(padding),
                score = score,
                total = questions.size,
                onRetry = {
                    index = 0; selected = -1; score = 0; finished = false; round++
                },
                onDone = onBack
            )
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    LinearProgressIndicator(
                        progress = { (index + 1) / questions.size.toFloat() },
                        modifier = Modifier
                            .weight(1f)
                            .height(8.dp)
                            .clip(RoundedCornerShape(4.dp)),
                        strokeCap = StrokeCap.Round
                    )
                    Spacer(Modifier.width(12.dp))
                    Text(
                        "${index + 1}/${questions.size}",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Spacer(Modifier.height(20.dp))
                Text(
                    q.question,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(Modifier.height(16.dp))
                q.options.forEachIndexed { i, option ->
                    val answered = selected >= 0
                    val isCorrect = i == q.correctIndex
                    val isSelected = i == selected
                    val container by animateColorAsState(
                        when {
                            answered && isCorrect -> Color(0xFF1B5E20).copy(alpha = 0.15f)
                            answered && isSelected -> Color(0xFFB71C1C).copy(alpha = 0.12f)
                            else -> MaterialTheme.colorScheme.surface
                        }, label = "optionColor"
                    )
                    val border = when {
                        answered && isCorrect -> Color(0xFF2E7D32)
                        answered && isSelected -> Color(0xFFC62828)
                        else -> MaterialTheme.colorScheme.outline.copy(alpha = 0.4f)
                    }
                    Card(
                        onClick = { if (!answered) { selected = i; if (i == q.correctIndex) score++ } },
                        shape = RoundedCornerShape(14.dp),
                        colors = CardDefaults.cardColors(containerColor = container),
                        border = androidx.compose.foundation.BorderStroke(1.5.dp, border),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 5.dp)
                    ) {
                        Row(
                            Modifier.padding(14.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(28.dp)
                                    .clip(CircleShape)
                                    .background(MaterialTheme.colorScheme.primaryContainer),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    "${'A' + i}",
                                    style = MaterialTheme.typography.labelMedium,
                                    color = MaterialTheme.colorScheme.onPrimaryContainer
                                )
                            }
                            Spacer(Modifier.width(12.dp))
                            Text(option, style = MaterialTheme.typography.bodyMedium, modifier = Modifier.weight(1f))
                            if (answered && isCorrect) {
                                Icon(Icons.Filled.CheckCircle, null, tint = Color(0xFF2E7D32), modifier = Modifier.size(20.dp))
                            } else if (answered && isSelected) {
                                Icon(Icons.Filled.Close, null, tint = Color(0xFFC62828), modifier = Modifier.size(20.dp))
                            }
                        }
                    }
                }
                AnimatedVisibility(
                    visible = selected >= 0,
                    enter = fadeIn() + slideInVertically { it / 3 }
                ) {
                    Column {
                        Spacer(Modifier.height(12.dp))
                        Card(
                            shape = RoundedCornerShape(14.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                                contentColor = MaterialTheme.colorScheme.onTertiaryContainer
                            )
                        ) {
                            Column(Modifier.padding(14.dp)) {
                                Text(
                                    if (selected == q.correctIndex) "✅ Correct!" else "❌ Not quite.",
                                    style = MaterialTheme.typography.titleSmall
                                )
                                Spacer(Modifier.height(4.dp))
                                Text(q.explanation, style = MaterialTheme.typography.bodySmall)
                            }
                        }
                        Spacer(Modifier.height(14.dp))
                        Button(
                            onClick = {
                                if (index == questions.size - 1) finished = true
                                else { index++; selected = -1 }
                            },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(14.dp)
                        ) {
                            Text(if (index == questions.size - 1) "See results" else "Next question")
                        }
                    }
                }
                Spacer(Modifier.height(24.dp))
            }
        }
    }
}

@Composable
private fun QuizResult(
    modifier: Modifier,
    score: Int,
    total: Int,
    onRetry: () -> Unit,
    onDone: () -> Unit
) {
    val percent = (score * 100) / total
    Box(modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                when {
                    percent >= 90 -> "🏆"
                    percent >= 70 -> "🎉"
                    percent >= 50 -> "💪"
                    else -> "📚"
                },
                fontSize = 64.sp
            )
            Spacer(Modifier.height(12.dp))
            Text(
                "$score / $total",
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.Bold
            )
            Text(
                "$percent% · " + when {
                    percent >= 90 -> "Outstanding!"
                    percent >= 70 -> "Great work!"
                    percent >= 50 -> "Getting there - review and retry."
                    else -> "Time to revisit the handbook sections."
                },
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(28.dp))
            Button(onClick = onRetry, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(14.dp)) {
                Icon(Icons.Filled.Refresh, null, modifier = Modifier.size(18.dp))
                Spacer(Modifier.width(8.dp))
                Text("Play again (shuffled)")
            }
            Spacer(Modifier.height(10.dp))
            OutlinedButton(onClick = onDone, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(14.dp)) {
                Text("Back to categories")
            }
        }
        if (percent >= 70) Confetti(Modifier.fillMaxSize())
    }
}

/** Lightweight one-shot confetti burst drawn on a Canvas. */
@Composable
private fun Confetti(modifier: Modifier = Modifier) {
    val progress = remember { Animatable(0f) }
    LaunchedEffect(Unit) { progress.animateTo(1f, tween(2600, easing = FastOutSlowInEasing)) }
    val pieces = remember {
        List(70) {
            ConfettiPiece(
                x = Random.nextFloat(),
                delay = Random.nextFloat() * 0.25f,
                speed = 0.7f + Random.nextFloat() * 0.6f,
                sway = Random.nextFloat() * 2f * PI.toFloat(),
                size = 5f + Random.nextFloat() * 7f,
                color = listOf(
                    Color(0xFF26A69A), Color(0xFFFFA726), Color(0xFFAB47BC),
                    Color(0xFFEF5350), Color(0xFF66BB6A), Color(0xFF42A5F5)
                ).random()
            )
        }
    }
    Canvas(modifier) {
        val t = progress.value
        pieces.forEach { p ->
            val local = ((t - p.delay) / (1f - p.delay)).coerceIn(0f, 1f)
            if (local <= 0f) return@forEach
            val y = -20f + local * p.speed * (size.height + 60f)
            val x = p.x * size.width + sin(p.sway + local * 6f) * 30f
            val alpha = (1f - local * 0.7f).coerceIn(0f, 1f)
            drawCircle(
                color = p.color.copy(alpha = alpha),
                radius = p.size,
                center = Offset(x, y)
            )
        }
    }
}

private data class ConfettiPiece(
    val x: Float, val delay: Float, val speed: Float,
    val sway: Float, val size: Float, val color: Color
)

// ---------------------------------------------------------------------------
// FLASHCARDS
// ---------------------------------------------------------------------------

@Composable
fun FlashcardHomeScreen(onDeckClick: (String) -> Unit, onBack: () -> Unit) {
    ToolScaffold("Flashcards", onBack) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Text(
                    "Tap a card to flip it. Active recall beats re-reading - say the answer out loud before you flip.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            items(StudyTools.flashcardDecks, key = { it.id }) { deck ->
                DeckCard(deck, onClick = { onDeckClick(deck.id) })
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DeckCard(deck: FlashcardDeck, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            Modifier
                .background(
                    Brush.linearGradient(
                        listOf(Color(0xFF1565C0).copy(alpha = 0.12f), Color(0xFF64B5F6).copy(alpha = 0.25f))
                    )
                )
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(deck.emoji, fontSize = 30.sp)
            Spacer(Modifier.width(14.dp))
            Column(Modifier.weight(1f)) {
                Text(deck.title, style = MaterialTheme.typography.titleSmall)
                Text(
                    "${deck.cards.size} cards",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
fun FlashcardPlayScreen(deckId: String, onBack: () -> Unit) {
    val deck = StudyTools.flashcardDecks.firstOrNull { it.id == deckId }
    if (deck == null) {
        ToolScaffold("Flashcards", onBack) { padding -> EmptyState("Deck not found", padding) }
        return
    }
    var order by remember { mutableStateOf(deck.cards.indices.toList()) }
    var pos by remember { mutableIntStateOf(0) }
    var flipped by remember { mutableStateOf(false) }
    val card = deck.cards[order[pos]]

    ToolScaffold("${deck.emoji} ${deck.title}", onBack) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LinearProgressIndicator(
                progress = { (pos + 1) / order.size.toFloat() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .clip(RoundedCornerShape(4.dp)),
                strokeCap = StrokeCap.Round
            )
            Spacer(Modifier.height(8.dp))
            Text(
                "Card ${pos + 1} of ${order.size}",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(Modifier.height(20.dp))

            // 3D flip card
            val rotation by animateFloatAsState(
                targetValue = if (flipped) 180f else 0f,
                animationSpec = tween(450, easing = FastOutSlowInEasing),
                label = "flip"
            )
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .graphicsLayer {
                        rotationY = rotation
                        cameraDistance = 14f * density
                    }
                    .clickable { flipped = !flipped },
                shape = RoundedCornerShape(22.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.linearGradient(
                                if (rotation <= 90f) listOf(Color(0xFF0B6E4F), Color(0xFF14A085))
                                else listOf(Color(0xFF4527A0), Color(0xFF7E57C2))
                            )
                        )
                        .padding(24.dp),
                    contentAlignment = Alignment.Center
                ) {
                    if (rotation <= 90f) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("QUESTION", style = MaterialTheme.typography.labelSmall, color = Color.White.copy(alpha = 0.7f))
                            Spacer(Modifier.height(12.dp))
                            Text(
                                card.front,
                                style = MaterialTheme.typography.titleLarge,
                                color = Color.White,
                                textAlign = TextAlign.Center
                            )
                            Spacer(Modifier.height(16.dp))
                            Text("tap to flip 👆", style = MaterialTheme.typography.bodySmall, color = Color.White.copy(alpha = 0.7f))
                        }
                    } else {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.graphicsLayer { rotationY = 180f }
                        ) {
                            Text("ANSWER", style = MaterialTheme.typography.labelSmall, color = Color.White.copy(alpha = 0.7f))
                            Spacer(Modifier.height(12.dp))
                            Text(
                                card.back,
                                style = MaterialTheme.typography.bodyLarge,
                                color = Color.White,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }

            Spacer(Modifier.height(24.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                OutlinedButton(
                    onClick = { if (pos > 0) { pos--; flipped = false } },
                    enabled = pos > 0,
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(14.dp)
                ) { Text("← Previous") }
                Button(
                    onClick = {
                        if (pos < order.size - 1) { pos++; flipped = false }
                        else { pos = 0; flipped = false }
                    },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(14.dp)
                ) { Text(if (pos < order.size - 1) "Next →" else "Restart ↻") }
            }
            Spacer(Modifier.height(12.dp))
            OutlinedButton(
                onClick = { order = order.shuffled(); pos = 0; flipped = false },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp)
            ) {
                Icon(Icons.Filled.Casino, null, modifier = Modifier.size(18.dp))
                Spacer(Modifier.width(8.dp))
                Text("Shuffle deck")
            }
        }
    }
}

// ---------------------------------------------------------------------------
// CALCULATORS
// ---------------------------------------------------------------------------

private data class CalcInfo(val id: String, val title: String, val subtitle: String, val emoji: String)

private val calcs = listOf(
    CalcInfo("bmi", "BMI", "Body mass index & category", "⚖️"),
    CalcInfo("drip", "IV Drip Rate", "Drops/min & pump mL/hr", "💧"),
    CalcInfo("fluids", "Maintenance Fluids", "4-2-1 rule (mL/hr)", "🧃"),
    CalcInfo("gcs", "Glasgow Coma Scale", "Eye + Verbal + Motor", "🧠"),
    CalcInfo("apgar", "APGAR Score", "Newborn assessment at 1 & 5 min", "👶")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculatorHomeScreen(onCalcClick: (String) -> Unit, onBack: () -> Unit) {
    ToolScaffold("Clinical Calculators", onBack) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Text(
                    "Educational tools - always double-check against local protocols before clinical use.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            items(calcs, key = { it.id }) { c ->
                Card(
                    onClick = { onCalcClick(c.id) },
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Row(Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                        Text(c.emoji, fontSize = 28.sp)
                        Spacer(Modifier.width(14.dp))
                        Column {
                            Text(c.title, style = MaterialTheme.typography.titleSmall)
                            Text(c.subtitle, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun NumberField(value: String, onChange: (String) -> Unit, label: String, modifier: Modifier = Modifier) {
    OutlinedTextField(
        value = value,
        onValueChange = onChange,
        label = { Text(label) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
        shape = RoundedCornerShape(14.dp),
        modifier = modifier.fillMaxWidth()
    )
}

@Composable
private fun ResultCard(value: String, caption: String) {
    Card(
        shape = RoundedCornerShape(18.dp),
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box(
            Modifier
                .background(Brush.linearGradient(heroGradient))
                .padding(22.dp)
        ) {
            Column {
                Text(value, style = MaterialTheme.typography.headlineMedium, color = Color.White)
                Spacer(Modifier.height(4.dp))
                Text(caption, style = MaterialTheme.typography.bodySmall, color = Color.White.copy(alpha = 0.85f))
            }
        }
    }
}

@Composable
fun CalculatorScreen(calcId: String, onBack: () -> Unit) {
    val info = calcs.firstOrNull { it.id == calcId }
    ToolScaffold(info?.let { "${it.emoji} ${it.title}" } ?: "Calculator", onBack) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            when (calcId) {
                "bmi" -> BmiCalc()
                "drip" -> DripCalc()
                "fluids" -> FluidsCalc()
                "gcs" -> GcsCalc()
                "apgar" -> ApgarCalc()
                else -> Text("Calculator not found")
            }
            Text(
                "For education only. Verify with current local guidelines and senior supervision before any clinical application.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(Modifier.height(20.dp))
        }
    }
}

@Composable
private fun BmiCalc() {
    var weight by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    NumberField(weight, { weight = it }, "Weight (kg)")
    NumberField(height, { height = it }, "Height (cm)")
    val result = Calculators.bmi(weight.toDoubleOrNull() ?: 0.0, height.toDoubleOrNull() ?: 0.0)
    if (result != null) {
        ResultCard("${result.bmi} kg/m²", result.category)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DripCalc() {
    var volume by remember { mutableStateOf("") }
    var hours by remember { mutableStateOf("") }
    var factor by remember { mutableIntStateOf(20) }
    NumberField(volume, { volume = it }, "Volume (mL)")
    NumberField(hours, { hours = it }, "Time (hours)")
    Text("Giving set (drops/mL)", style = MaterialTheme.typography.labelLarge)
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        listOf(10, 15, 20, 60).forEach { f ->
            FilterChip(selected = factor == f, onClick = { factor = f }, label = { Text("$f") })
        }
    }
    val minutes = (hours.toDoubleOrNull() ?: 0.0) * 60.0
    val drops = Calculators.dripRate(volume.toDoubleOrNull() ?: 0.0, minutes, factor)
    val pump = Calculators.infusionRate(volume.toDoubleOrNull() ?: 0.0, minutes)
    if (drops != null && pump != null) {
        ResultCard("$drops drops/min", "Pump rate: $pump mL/hr · giving set $factor gtt/mL")
    }
}

@Composable
private fun FluidsCalc() {
    var weight by remember { mutableStateOf("") }
    NumberField(weight, { weight = it }, "Body weight (kg)")
    val rate = Calculators.maintenanceFluidMlPerHr(weight.toDoubleOrNull() ?: 0.0)
    if (rate != null) {
        ResultCard("$rate mL/hr", "Holliday-Segar 4-2-1 rule · ~${(rate * 24).toInt()} mL/day. Adjust for deficits, losses and clinical state.")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun GcsCalc() {
    var eye by remember { mutableIntStateOf(4) }
    var verbal by remember { mutableIntStateOf(5) }
    var motor by remember { mutableIntStateOf(6) }

    Text("Eye opening (E)", style = MaterialTheme.typography.labelLarge)
    Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
        (1..4).forEach { v ->
            FilterChip(selected = eye == v, onClick = { eye = v }, label = { Text("$v") })
        }
    }
    Text(
        when (eye) { 4 -> "Spontaneous"; 3 -> "To speech"; 2 -> "To pain"; else -> "None" },
        style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant
    )
    Text("Verbal response (V)", style = MaterialTheme.typography.labelLarge)
    Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
        (1..5).forEach { v ->
            FilterChip(selected = verbal == v, onClick = { verbal = v }, label = { Text("$v") })
        }
    }
    Text(
        when (verbal) { 5 -> "Orientated"; 4 -> "Confused"; 3 -> "Words"; 2 -> "Sounds"; else -> "None" },
        style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant
    )
    Text("Motor response (M)", style = MaterialTheme.typography.labelLarge)
    Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
        (1..6).forEach { v ->
            FilterChip(selected = motor == v, onClick = { motor = v }, label = { Text("$v") })
        }
    }
    Text(
        when (motor) { 6 -> "Obeys commands"; 5 -> "Localises pain"; 4 -> "Withdraws"; 3 -> "Flexion (decorticate)"; 2 -> "Extension (decerebrate)"; else -> "None" },
        style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant
    )
    val result = Calculators.gcs(eye, verbal, motor)
    if (result != null) {
        ResultCard("GCS ${result.total}/15", "E${eye} V${verbal} M${motor} · ${result.severity}. GCS ≤ 8: consider airway protection.")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ApgarCalc() {
    val labels = listOf("Appearance", "Pulse", "Grimace", "Activity", "Respiration")
    val hints = listOf(
        listOf("Blue/pale", "Body pink, blue limbs", "Pink all over"),
        listOf("Absent", "< 100/min", "≥ 100/min"),
        listOf("None", "Grimace", "Cough/sneeze/cry"),
        listOf("Limp", "Some flexion", "Active movement"),
        listOf("Absent", "Slow/irregular", "Good, crying")
    )
    val scores = remember { androidx.compose.runtime.mutableStateListOf(2, 2, 2, 2, 2) }
    labels.forEachIndexed { i, label ->
        Text(label, style = MaterialTheme.typography.labelLarge)
        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            (0..2).forEach { v ->
                FilterChip(
                    selected = scores[i] == v,
                    onClick = { scores[i] = v },
                    label = { Text("$v") }
                )
            }
        }
        Text(
            hints[i][scores[i]],
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
    val result = Calculators.apgar(scores.toList())
    if (result != null) {
        ResultCard("APGAR ${result.total}/10", "${result.interpretation} · score at 1 and 5 minutes (and 10 if low)")
    }
}

// ---------------------------------------------------------------------------
// GLOSSARY
// ---------------------------------------------------------------------------

@Composable
fun GlossaryScreen(onBack: () -> Unit) {
    var query by remember { mutableStateOf("") }
    val entries = remember(query) {
        val q = query.trim().lowercase()
        if (q.isEmpty()) StudyTools.glossary
        else StudyTools.glossary.filter {
            it.term.lowercase().contains(q) || it.definition.lowercase().contains(q)
        }
    }
    ToolScaffold("Medical Glossary", onBack) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp)
        ) {
            OutlinedTextField(
                value = query,
                onValueChange = { query = it },
                placeholder = { Text("Search ${StudyTools.glossary.size} terms…") },
                leadingIcon = { Icon(Icons.Filled.Search, null) },
                singleLine = true,
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(12.dp))
            if (entries.isEmpty()) {
                Text(
                    "No terms match \"$query\".",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    contentPadding = PaddingValues(bottom = 24.dp)
                ) {
                    items(entries, key = { it.term }) { entry -> GlossaryCard(entry) }
                }
            }
        }
    }
}

@Composable
private fun GlossaryCard(entry: GlossaryEntry) {
    Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(Modifier.padding(14.dp)) {
            Text(
                entry.term,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(Modifier.height(4.dp))
            Text(entry.definition, style = MaterialTheme.typography.bodySmall)
        }
    }
}
