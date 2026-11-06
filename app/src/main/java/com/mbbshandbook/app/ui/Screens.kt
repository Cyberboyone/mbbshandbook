package com.mbbshandbook.app.ui

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Style
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mbbshandbook.app.Bookmarks
import com.mbbshandbook.app.Progress
import com.mbbshandbook.app.ads.AdPrivacyOptionsButton
import com.mbbshandbook.app.ads.TestAdBanner
import com.mbbshandbook.app.ads.itemsWithInlineAds
import com.mbbshandbook.app.data.ContentBlock
import com.mbbshandbook.app.data.Handbook
import com.mbbshandbook.app.data.Section
import com.mbbshandbook.app.data.Topic

internal val sectionEmojis = mapOf(
    "start" to "🧭",
    "preclinical" to "🔬",
    "paraclinical" to "🧫",
    "clinical" to "🏥",
    "skills" to "🩺",
    "exams" to "📝",
    "ethics" to "⚖️",
    "wellbeing" to "🌿",
    "resources" to "📚",
    "mnemonics" to "🧠",
    "emergencies" to "🚨"
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onSectionClick: (String) -> Unit,
    onSearchClick: () -> Unit,
    onBookmarksClick: () -> Unit,
    onQuizClick: () -> Unit,
    onFlashcardsClick: () -> Unit,
    onCalculatorsClick: () -> Unit,
    onGlossaryClick: () -> Unit,
    onSpecialtiesClick: () -> Unit,
    onContinueReading: () -> Unit
) {
    val percent = Progress.percent()
    val readCount = Progress.readIds.count { id -> Handbook.allTopics.any { it.id == id } }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        bottomBar = {
            Column {
                TestAdBanner()
                HomeBottomBar(
                    onSearchClick = onSearchClick,
                    onBookmarksClick = onBookmarksClick
                )
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // ---- Greeting ----
            item {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Column(Modifier.weight(1f)) {
                        Text(
                            "MBBS Handbook",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            "Your pocket study companion",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    Image(
                        painter = painterResource(com.mbbshandbook.app.R.drawable.ic_brand_mark),
                        contentDescription = null,
                        modifier = Modifier
                            .size(42.dp)
                            .clip(RoundedCornerShape(50))
                    )
                }
            }

            // ---- Glowing progress hero ----
            item {
                GlowPanel(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        "Study progress",
                        style = MaterialTheme.typography.labelMedium,
                        color = Color.White.copy(alpha = 0.75f)
                    )
                    Spacer(Modifier.height(6.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            "$percent%",
                            style = MaterialTheme.typography.displaySmall,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Spacer(Modifier.width(12.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                Icons.Filled.ArrowUpward,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(14.dp)
                            )
                            Spacer(Modifier.width(4.dp))
                            Text(
                                "$readCount/${Handbook.allTopics.size} topics",
                                style = MaterialTheme.typography.labelSmall,
                                color = Color.White.copy(alpha = 0.85f)
                            )
                        }
                    }
                    Spacer(Modifier.height(6.dp))
                    LinearProgressIndicator(
                        progress = { percent / 100f },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(6.dp)
                            .clip(RoundedCornerShape(3.dp)),
                        color = MaterialTheme.colorScheme.primary,
                        trackColor = Color.White.copy(alpha = 0.12f),
                        strokeCap = StrokeCap.Round
                    )
                    Spacer(Modifier.height(16.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        PillButton(
                            text = "Continue",
                            icon = Icons.Filled.PlayArrow,
                            onClick = onContinueReading,
                            modifier = Modifier.weight(1f)
                        )
                        PillButton(
                            text = "Search",
                            icon = Icons.Filled.Search,
                            onClick = onSearchClick,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }

            // ---- Quick actions ----
            item {
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    QuickTile("Quizzes", Icons.Filled.Quiz, onQuizClick, Modifier.weight(1f))
                    QuickTile("Flashcards", Icons.Filled.Style, onFlashcardsClick, Modifier.weight(1f))
                    QuickTile("Calculators", Icons.Filled.Calculate, onCalculatorsClick, Modifier.weight(1f))
                }
            }

            // ---- Filter-style shortcuts ----
            item {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.horizontalScroll(rememberScrollState())
                ) {
                    FilterPill("Specialties", selected = true, onClick = onSpecialtiesClick)
                    FilterPill("Glossary", selected = false, onClick = onGlossaryClick)
                    FilterPill("Saved", selected = false, onClick = onBookmarksClick)
                    FilterPill("Mnemonics", selected = false, onClick = { onSectionClick("mnemonics") })
                }
            }

            // ---- Handbook sections ----
            item {
                Text(
                    "Handbook",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
            itemsWithInlineAds(Handbook.sections, key = { it.id }) { section ->
                SectionRow(section = section, onClick = { onSectionClick(section.id) })
            }
            item {
                Text(
                    "Educational guidance for students - always follow your school's curriculum and local clinical guidelines.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = 8.dp, start = 4.dp, end = 4.dp)
                )
            }
            item {
                AdPrivacyOptionsButton(modifier = Modifier.fillMaxWidth())
            }
        }
    }
}

/** Dark fintech-style list row: gradient icon tile, title/subtitle, progress on the right. */
@Composable
private fun SectionRow(section: Section, onClick: () -> Unit) {
    val readIn = section.topics.count { Progress.isRead(it.id) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.surface)
            .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(16.dp))
            .clickable(onClick = onClick)
            .padding(14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(44.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Brush.linearGradient(sectionGradient(section.id))),
            contentAlignment = Alignment.Center
        ) {
            Text(sectionEmojis[section.id] ?: "📖", fontSize = 22.sp)
        }
        Spacer(Modifier.width(12.dp))
        Column(Modifier.weight(1f)) {
            Text(section.title, style = MaterialTheme.typography.titleSmall)
            Text(
                "${section.topics.size} topics",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Column(horizontalAlignment = Alignment.End) {
            Text(
                if (readIn == section.topics.size) "✓ complete" else "$readIn/${section.topics.size} read",
                style = MaterialTheme.typography.labelMedium,
                color = if (readIn == section.topics.size) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.onSurface
            )
            Text(
                "open section",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Spacer(Modifier.width(6.dp))
        Icon(
            Icons.Filled.ChevronRight,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SectionScreen(sectionId: String, onTopicClick: (String) -> Unit, onBack: () -> Unit) {
    val section = Handbook.sectionById(sectionId)
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(section?.title ?: "Section") },
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
        bottomBar = { TestAdBanner() }
    ) { padding ->
        if (section == null) {
            EmptyState("Section not found", padding)
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                item {
                    GlowPanel(
                        modifier = Modifier.fillMaxWidth(),
                        gradient = sectionGradient(section.id)
                    ) {
                        Text(sectionEmojis[section.id] ?: "📖", fontSize = 30.sp)
                        Spacer(Modifier.height(8.dp))
                        Text(
                            section.title,
                            style = MaterialTheme.typography.titleLarge,
                            color = Color.White
                        )
                        Text(
                            section.description,
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.White.copy(alpha = 0.85f)
                        )
                    }
                    Spacer(Modifier.height(4.dp))
                }
                itemsWithInlineAds(section.topics, key = { it.id }) { topic ->
                    TopicRow(topic = topic, onClick = { onTopicClick(topic.id) })
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TopicRow(topic: Topic, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(Modifier.weight(1f)) {
                Text(
                    topic.title,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    topic.summary,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Spacer(Modifier.width(8.dp))
            if (Progress.isRead(topic.id)) {
                Icon(
                    Icons.Filled.CheckCircle,
                    contentDescription = "Read",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(Modifier.width(6.dp))
            }
            if (Bookmarks.contains(topic.id)) {
                Icon(
                    Icons.Filled.Bookmark,
                    contentDescription = "Bookmarked",
                    tint = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier.size(18.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopicScreen(topicId: String, onBack: () -> Unit) {
    val topic = Handbook.topicById(topicId)
    val section = Handbook.sectionForTopic(topicId)
    val context = LocalContext.current
    val isBookmarked = Bookmarks.contains(topicId)

    LaunchedEffect(topicId) {
        if (topic != null) Progress.markRead(topicId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(section?.title ?: "Topic", style = MaterialTheme.typography.titleMedium) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    if (topic != null) {
                        IconButton(onClick = { Bookmarks.toggle(topicId) }) {
                            Icon(
                                if (isBookmarked) Icons.Filled.Bookmark else Icons.Filled.BookmarkBorder,
                                contentDescription = if (isBookmarked) "Remove bookmark" else "Add bookmark",
                                tint = if (isBookmarked) MaterialTheme.colorScheme.primary
                                else MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                        IconButton(onClick = {
                            val text = buildString {
                                append(topic.title)
                                append("\n\n")
                                topic.blocks.forEach { block ->
                                    when (block) {
                                        is ContentBlock.Heading -> append("\n").append(block.text).append("\n")
                                        is ContentBlock.Paragraph -> append(block.text).append("\n\n")
                                        is ContentBlock.Bullets -> block.items.forEach {
                                            append("• ").append(it).append("\n")
                                        }
                                        is ContentBlock.Tip -> append("\nTip: ").append(block.text).append("\n")
                                    }
                                    append("\n")
                                }
                                append("- MBBS Students Handbook app")
                            }
                            val intent = Intent(Intent.ACTION_SEND).apply {
                                type = "text/plain"
                                putExtra(Intent.EXTRA_SUBJECT, topic.title)
                                putExtra(Intent.EXTRA_TEXT, text)
                            }
                            context.startActivity(Intent.createChooser(intent, "Share topic"))
                        }) {
                            Icon(Icons.Filled.Share, contentDescription = "Share")
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { padding ->
        if (topic == null) {
            EmptyState("Topic not found", padding)
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 32.dp),
                verticalArrangement = Arrangement.spacedBy(0.dp)
            ) {
                item {
                    GlowPanel(
                        modifier = Modifier.fillMaxWidth(),
                        gradient = sectionGradient(section?.id ?: ""),
                        cornerRadius = 18.dp
                    ) {
                        Text(
                            "${sectionEmojis[section?.id] ?: "📖"} ${section?.title ?: "Handbook"}",
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.White
                        )
                    }
                    Spacer(Modifier.height(14.dp))
                    Text(
                        topic.title,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        topic.summary,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(Modifier.height(12.dp))
                    HorizontalDivider(color = MaterialTheme.colorScheme.surfaceVariant)
                    Spacer(Modifier.height(12.dp))
                }
                items(topic.blocks) { block ->
                    BlockView(block)
                }
            }
        }
    }
}

@Composable
private fun BlockView(block: ContentBlock) {
    when (block) {
        is ContentBlock.Heading -> {
            Text(
                block.text,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(top = 14.dp, bottom = 6.dp)
            )
        }
        is ContentBlock.Paragraph -> {
            Text(
                block.text,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(vertical = 6.dp)
            )
        }
        is ContentBlock.Bullets -> {
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                block.items.forEach { item ->
                    Row(Modifier.padding(vertical = 2.dp)) {
                        Text(
                            "•",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(Modifier.width(10.dp))
                        Text(item, style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
            Spacer(Modifier.height(6.dp))
        }
        is ContentBlock.Tip -> {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                    contentColor = MaterialTheme.colorScheme.onTertiaryContainer
                ),
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
            ) {
                Row(Modifier.padding(14.dp)) {
                    Icon(
                        Icons.Filled.Lightbulb,
                        contentDescription = "Tip",
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(Modifier.width(10.dp))
                    Text(block.text, style = MaterialTheme.typography.bodySmall)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(onTopicClick: (String) -> Unit, onBack: () -> Unit) {
    var query by remember { mutableStateOf("") }
    val results = remember(query) { Handbook.search(query) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Search") },
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
        containerColor = MaterialTheme.colorScheme.background
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp)
        ) {
            OutlinedTextField(
                value = query,
                onValueChange = { query = it },
                placeholder = { Text("Try 'anatomy', 'OSCE', 'consent'…") },
                leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) },
                singleLine = true,
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(12.dp))
            when {
                query.isBlank() -> Text(
                    "Search across all ${Handbook.allTopics.size} topics.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                results.isEmpty() -> Text(
                    "No topics match \"$query\".",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                else -> LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    item {
                        Text(
                            "${results.size} result${if (results.size == 1) "" else "s"}",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                    itemsWithInlineAds(results, key = { it.id }) { topic ->
                        TopicRow(topic = topic, onClick = { onTopicClick(topic.id) })
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookmarksScreen(onTopicClick: (String) -> Unit, onBack: () -> Unit) {
    val bookmarked = Handbook.allTopics.filter { Bookmarks.contains(it.id) }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Bookmarks") },
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
        containerColor = MaterialTheme.colorScheme.background
    ) { padding ->
        if (bookmarked.isEmpty()) {
            EmptyState(
                "No bookmarks yet.\nTap the bookmark icon on any topic to save it here.",
                padding
            )
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                itemsWithInlineAds(bookmarked, key = { it.id }) { topic ->
                    TopicRow(topic = topic, onClick = { onTopicClick(topic.id) })
                }
            }
        }
    }
}

@Composable
internal fun EmptyState(message: String, padding: PaddingValues) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                Icons.AutoMirrored.Filled.MenuBook,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.outline,
                modifier = Modifier.size(48.dp)
            )
            Spacer(Modifier.height(12.dp))
            Text(
                message,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
