package com.mbbshandbook.app.ui

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.LocalLibrary
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mbbshandbook.app.Bookmarks
import com.mbbshandbook.app.Progress
import com.mbbshandbook.app.ads.AdPrivacyOptionsButton
import com.mbbshandbook.app.ads.TestAdBanner
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
    onSpecialtiesClick: () -> Unit
) {
    val percent = Progress.percent()
    val readCount = Progress.readIds.count { id -> Handbook.allTopics.any { it.id == id } }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(com.mbbshandbook.app.R.drawable.ic_brand_mark),
                            contentDescription = null,
                            modifier = Modifier
                                .size(34.dp)
                                .clip(RoundedCornerShape(9.dp))
                        )
                        Spacer(Modifier.width(10.dp))
                        Text("MBBS Handbook", fontWeight = FontWeight.Bold)
                    }
                },
                actions = {
                    IconButton(onClick = onSearchClick) {
                        Icon(Icons.Filled.Search, contentDescription = "Search")
                    }
                    IconButton(onClick = onBookmarksClick) {
                        Icon(Icons.Filled.Bookmarks, contentDescription = "Bookmarks")
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
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // ---- Hero ----
            item {
                Box {
                    BannerImage(res = com.mbbshandbook.app.R.drawable.hero, height = 210.dp)
                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(18.dp)
                    ) {
                        Text(
                            "MBBS Students\nHandbook",
                            style = MaterialTheme.typography.headlineMedium,
                            color = Color.White
                        )
                        Text(
                            "Your pocket companion through medical school",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.White.copy(alpha = 0.85f)
                        )
                    }
                }
            }

            // ---- Progress ----
            item {
                Card(
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                ) {
                    Column(Modifier.padding(16.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                "📈 Your progress",
                                style = MaterialTheme.typography.titleSmall,
                                modifier = Modifier.weight(1f)
                            )
                            Text(
                                "$percent%",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Spacer(Modifier.height(10.dp))
                        LinearProgressIndicator(
                            progress = { percent / 100f },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(8.dp)
                                .clip(RoundedCornerShape(4.dp)),
                            strokeCap = StrokeCap.Round
                        )
                        Spacer(Modifier.height(8.dp))
                        Text(
                            "$readCount of ${Handbook.allTopics.size} topics read · ${Handbook.sections.size} sections",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }

            // ---- Study tools ----
            item {
                Text(
                    "Study Tools",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
            item {
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    ToolCard("Quizzes", "Test yourself", Icons.Filled.Quiz,
                        listOf(Color(0xFF7B1FA2), Color(0xFFBA68C8)), Modifier.weight(1f), onQuizClick)
                    ToolCard("Flashcards", "Flip & recall", Icons.Filled.Style,
                        listOf(Color(0xFF1565C0), Color(0xFF64B5F6)), Modifier.weight(1f), onFlashcardsClick)
                }
            }
            item {
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    ToolCard("Calculators", "Clinical maths", Icons.Filled.Calculate,
                        listOf(Color(0xFFC62828), Color(0xFFE57373)), Modifier.weight(1f), onCalculatorsClick)
                    ToolCard("Glossary", "Med terms A-Z", Icons.Filled.LocalLibrary,
                        listOf(Color(0xFF00695C), Color(0xFF4DB6AC)), Modifier.weight(1f), onGlossaryClick)
                }
            }

            // ---- Specialties feature ----
            item {
                SpecialtiesFeatureCard(onClick = onSpecialtiesClick)
            }

            // ---- Sections ----
            item {
                Text(
                    "Handbook",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
            items(Handbook.sections, key = { it.id }) { section ->
                SectionBannerCard(section = section, onClick = { onSectionClick(section.id) })
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ToolCard(
    title: String,
    subtitle: String,
    icon: ImageVector,
    gradient: List<Color>,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(18.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Box(
            Modifier
                .background(Brush.linearGradient(gradient))
                .padding(16.dp)
        ) {
            Column {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(Color.White.copy(alpha = 0.22f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(icon, contentDescription = null, tint = Color.White)
                }
                Spacer(Modifier.height(10.dp))
                Text(title, style = MaterialTheme.typography.titleSmall, color = Color.White)
                Text(
                    subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.White.copy(alpha = 0.85f)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SpecialtiesFeatureCard(onClick: () -> Unit) {
    Card(
        onClick = onClick,
        shape = RoundedCornerShape(18.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Box(
            Modifier
                .background(
                    Brush.linearGradient(
                        listOf(Color(0xFF4527A0), Color(0xFF7E57C2), Color(0xFFB39DDB))
                    )
                )
                .padding(18.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("🩺", fontSize = 34.sp)
                Spacer(Modifier.width(14.dp))
                Column(Modifier.weight(1f)) {
                    Text(
                        "Specialties & Roadmaps",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White
                    )
                    Text(
                        "${com.mbbshandbook.app.data.Specialties.all.size} careers · training paths · exams · lifestyle",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.White.copy(alpha = 0.88f)
                    )
                }
                Icon(
                    Icons.Filled.ChevronRight,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SectionBannerCard(section: Section, onClick: () -> Unit) {
    val readIn = section.topics.count { Progress.isRead(it.id) }
    Card(
        onClick = onClick,
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Box {
            BannerImage(res = sectionBanner(section.id), height = 130.dp, cornerRadius = 0.dp)
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(10.dp)
                    .clip(RoundedCornerShape(50))
                    .background(
                        Brush.linearGradient(sectionGradient(section.id))
                    )
                    .padding(horizontal = 10.dp, vertical = 4.dp)
            ) {
                Text(
                    if (readIn == section.topics.size) "✓ complete" else "$readIn/${section.topics.size} read",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.White
                )
            }
            Row(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    sectionEmojis[section.id] ?: "📖",
                    fontSize = 26.sp
                )
                Spacer(Modifier.width(10.dp))
                Column(Modifier.weight(1f)) {
                    Text(
                        section.title,
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White
                    )
                    Text(
                        section.description,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.White.copy(alpha = 0.85f),
                        maxLines = 1
                    )
                }
                Icon(
                    Icons.Filled.ChevronRight,
                    contentDescription = null,
                    tint = Color.White.copy(alpha = 0.9f)
                )
            }
        }
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
                    Box {
                        BannerImage(res = sectionBanner(section.id), height = 150.dp)
                        Column(
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .padding(16.dp)
                        ) {
                            Text(
                                "${sectionEmojis[section.id] ?: "📖"} ${section.title}",
                                style = MaterialTheme.typography.titleLarge,
                                color = Color.White
                            )
                            Text(
                                section.description,
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.White.copy(alpha = 0.85f)
                            )
                        }
                    }
                    Spacer(Modifier.height(4.dp))
                }
                items(section.topics, key = { it.id }) { topic ->
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
                    BannerImage(res = sectionBanner(section?.id ?: ""), height = 140.dp)
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
                    items(results, key = { it.id }) { topic ->
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
                items(bookmarked, key = { it.id }) { topic ->
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
