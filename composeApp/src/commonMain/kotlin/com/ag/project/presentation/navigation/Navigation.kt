package com.ag.project.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavArgument
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ag.project.presentation.screen.details.NewsDetailsScreen
import com.ag.project.presentation.screen.home.NewsHomeScreen
import com.ag.project.utils.Screen

@Composable
fun Navigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.rout
    ) {

        composable(Screen.Home.rout) {
            NewsHomeScreen(navHostController = navController)
        }

        composable(Screen.Details.rout + "/{newsImage}/{newsTitle}/{newsDescription}",
            arguments = listOf(
                navArgument("newsImage") { type = NavType.StringType },
                navArgument("newsTitle") { type = NavType.StringType },
                navArgument("newsDescription") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            NewsDetailsScreen(
                navHostController = navController,
                backStackEntry = backStackEntry
            )
        }


    }
}