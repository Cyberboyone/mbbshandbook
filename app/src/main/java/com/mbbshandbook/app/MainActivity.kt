package com.mbbshandbook.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mbbshandbook.app.ui.BookmarksScreen
import com.mbbshandbook.app.ui.HomeScreen
import com.mbbshandbook.app.ui.SearchScreen
import com.mbbshandbook.app.ui.SectionScreen
import com.mbbshandbook.app.ui.TopicScreen
import com.mbbshandbook.app.ui.theme.MbbsHandbookTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Bookmarks.init(this)
        enableEdgeToEdge()
        setContent {
            MbbsHandbookTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "home") {
                    composable("home") {
                        HomeScreen(
                            onSectionClick = { navController.navigate("section/$it") },
                            onSearchClick = { navController.navigate("search") },
                            onBookmarksClick = { navController.navigate("bookmarks") }
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
                }
            }
        }
    }
}
