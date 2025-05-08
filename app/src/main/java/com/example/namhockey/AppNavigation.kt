package com.example.namhockey

import HomePage
import NewsDetailScreen
import android.accounts.Account
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun AppNavigation(newsViewModel: NewsViewModel, youTubeViewModel: YouTubeViewModel, loginViewModel: LoginViewModel) {

val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "SignUp") {
        composable("SignUp") { Signup(navController) }
        composable("Login") { Login(navController, loginViewModel) }
        composable("home") { HomePage(navController, newsViewModel,youTubeViewModel)  }
        composable("account") { Accountpage(navController) }
        composable("findClub") { FindAClub(navController) }
        composable("registerClub") { RegisterTeam(navController) }
        composable("coach") { Coach(navController) }
        composable("profile") { PlayerPageEdit(navController) }
        composable("fixtures") {FixturesAndScores(navController)}

        composable(route = "newsDetail/{title}/{description}/{imageUrl}",
                arguments = listOf(
                    navArgument("title") { type = NavType.StringType },
                    navArgument("description") { type = NavType.StringType },
                    navArgument("imageUrl") { type = NavType.StringType }
                )
            ) {backStackEntry ->
            val title = backStackEntry.arguments?.getString("title")?.let { Uri.decode(it) }
            val description = backStackEntry.arguments?.getString("description")?.let { Uri.decode(it) }
            val imageUrl = backStackEntry.arguments?.getString("imageUrl")?.let { Uri.decode(it) }

            NewsDetailScreen(title?: "No title", description?: "No description ", imageUrl?:" ", navController)

        }

    }
}

