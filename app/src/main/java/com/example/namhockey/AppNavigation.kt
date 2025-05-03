package com.example.namhockey

import HomePage
import android.accounts.Account
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation() {
val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "Signup") {
        composable("SignUp") { Signup(navController) }
        composable("Login") { Login(navController) }
        composable("home") { HomePage(navController)  }
        composable("account") { Accountpage(navController) }
        composable("findClub") { FindAClub(navController) }
        composable("registerClub") { RegisterTeam(navController) }
        composable("coach") { Coach(navController) }

    }
}