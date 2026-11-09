package com.mbbshandbook.app

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mbbshandbook.app.ads.AdsController
import com.mbbshandbook.app.data.Handbook
import com.mbbshandbook.app.ui.AboutScreen
import com.mbbshandbook.app.ui.BookmarksScreen
import com.mbbshandbook.app.ui.CalculatorHomeScreen
import com.mbbshandbook.app.ui.CalculatorScreen
import com.mbbshandbook.app.ui.FlashcardHomeScreen
import com.mbbshandbook.app.ui.FlashcardPlayScreen
import com.mbbshandbook.app.ui.GlossaryScreen
import com.mbbshandbook.app.ui.HomeScreen
import com.mbbshandbook.app.ui.QuizHomeScreen
import com.mbbshandbook.app.ui.QuizPlayScreen
import com.mbbshandbook.app.ui.SearchScreen
import com.mbbshandbook.app.ui.SectionScreen
import com.mbbshandbook.app.ui.SpecialtiesScreen
import com.mbbshandbook.app.ui.SpecialtyDetailScreen
import com.mbbshandbook.app.ui.TopicScreen
import com.mbbshandbook.app.ui.theme.MbbsHandbookTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Bookmarks.init(this)
        Progress.init(this)
        QuizScores.init(this)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(Color.TRANSPARENT),
            navigationBarStyle = SystemBarStyle.dark(Color.TRANSPARENT)
        )
        AdsController.start(this)
        setContent {
            MbbsHandbookTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "home",
                    enterTransition = {
                        slideInHorizontally(tween(280)) { it / 4 } + fadeIn(tween(280))
                    },
                    exitTransition = { fadeOut(tween(200)) },
                    popEnterTransition = { fadeIn(tween(200)) },
                    popExitTransition = {
                        slideOutHorizontally(tween(280)) { it / 4 } + fadeOut(tween(280))
                    }
                ) {
                    composable("home") {
                        HomeScreen(
                            onSectionClick = { navController.navigate("section/$it") },
                            onSearchClick = { navController.navigate("search") },
                            onBookmarksClick = { navController.navigate("bookmarks") },
                            onQuizClick = { navController.navigate("quiz") },
                            onFlashcardsClick = { navController.navigate("flashcards") },
                            onCalculatorsClick = { navController.navigate("calculators") },
                            onGlossaryClick = { navController.navigate("glossary") },
                            onSpecialtiesClick = { navController.navigate("specialties") },
                            onContinueReading = {
                                val next = Handbook.allTopics.firstOrNull { !Progress.isRead(it.id) }
                                    ?: Handbook.allTopics.first()
                                navController.navigate("topic/${next.id}")
                            },
                            onAboutClick = { navController.navigate("about") }
                        )
                    }
                    composable("specialties") {
                        SpecialtiesScreen(
                            onSpecialtyClick = { navController.navigate("specialties/$it") },
                            onBack = { navController.popBackStack() }
                        )
                    }
                    composable("specialties/{specialtyId}") { entry ->
                        SpecialtyDetailScreen(
                            specialtyId = entry.arguments?.getString("specialtyId").orEmpty(),
                            onBack = { navController.popBackStack() }
                        )
                    }
                    composable("section/{sectionId}") { entry ->
                        val sectionId = entry.arguments?.getString("sectionId").orEmpty()
                        SectionScreen(
                            sectionId = sectionId,
                            onTopicClick = { navController.navigate("topic/$it") },
                            onBack = { navController.popBackStack() }
                        )
                    }
                    composable("topic/{topicId}") { entry ->
                        val topicId = entry.arguments?.getString("topicId").orEmpty()
                        TopicScreen(
                            topicId = topicId,
                            onBack = { navController.popBackStack() }
                        )
                    }
                    composable("search") {
                        SearchScreen(
                            onTopicClick = { navController.navigate("topic/$it") },
                            onBack = { navController.popBackStack() }
                        )
                    }
                    composable("bookmarks") {
                        BookmarksScreen(
                            onTopicClick = { navController.navigate("topic/$it") },
                            onBack = { navController.popBackStack() }
                        )
                    }
                    composable("quiz") {
                        QuizHomeScreen(
                            onCategoryClick = { navController.navigate("quiz/$it") },
                            onBack = { navController.popBackStack() }
                        )
                    }
                    composable("quiz/{categoryId}") { entry ->
                        QuizPlayScreen(
                            categoryId = entry.arguments?.getString("categoryId").orEmpty(),
                            onBack = { navController.popBackStack() }
                        )
                    }
                    composable("flashcards") {
                        FlashcardHomeScreen(
                            onDeckClick = { navController.navigate("flashcards/$it") },
                            onBack = { navController.popBackStack() }
                        )
                    }
                    composable("flashcards/{deckId}") { entry ->
                        FlashcardPlayScreen(
                            deckId = entry.arguments?.getString("deckId").orEmpty(),
                            onBack = { navController.popBackStack() }
                        )
                    }
                    composable("calculators") {
                        CalculatorHomeScreen(
                            onCalcClick = { navController.navigate("calculators/$it") },
                            onBack = { navController.popBackStack() }
                        )
                    }
                    composable("calculators/{calcId}") { entry ->
                        CalculatorScreen(
                            calcId = entry.arguments?.getString("calcId").orEmpty(),
                            onBack = { navController.popBackStack() }
                        )
                    }
                    composable("glossary") {
                        GlossaryScreen(onBack = { navController.popBackStack() })
                    }
                    composable("about") {
                        AboutScreen(onBack = { navController.popBackStack() })
                    }
                }
            }
        }
    }
}
