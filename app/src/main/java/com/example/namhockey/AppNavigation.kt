package com.example.namhockey

import HomePage
import androidx.compose.runtime.Composable
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

        composable("newsDetail/{title}/{description}/{imageUrl}",

            arguments = listOf(
                navArgument("title") { type = NavType.StringType },
                navArgument("description") { type = NavType.StringType },
                navArgument("imageUrl") { type = NavType.StringType }
            )

        ) { backStackEntry ->

            val title = backStackEntry.arguments?.getString("title")
            val description = backStackEntry.arguments?.getString("description")
            val imageUrl = backStackEntry.arguments?.getString("imageUrl")


            NewsDetailScreen(
                title = title,
                description = description,
                imageUrl = imageUrl,
                navController = navController
            )
        }




    }
}

