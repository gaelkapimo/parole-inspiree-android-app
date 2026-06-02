package com.example.paroleinspiree.route.rails

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.paroleinspiree.data.repository.AuthRepositoryImpl
import com.example.paroleinspiree.presentation.signin.AuthViewModel
import com.example.paroleinspiree.presentation.signin.AuthViewModelFactory
import com.example.paroleinspiree.presentation.signin.LoginScreen
import com.example.paroleinspiree.presentation.signin.MainNavUi
import com.google.firebase.auth.FirebaseAuth

@Composable
fun RootNavHost() {

    val authRepository = AuthRepositoryImpl(FirebaseAuth.getInstance())
    val authViewModel: AuthViewModel = viewModel(
        factory = AuthViewModelFactory(authRepository)
    )

    val navController = rememberNavController()
    val user = FirebaseAuth.getInstance().currentUser

    NavHost(
        navController = navController,
        startDestination = if (user != null) Route.MAIN else Route.LOGIN
    ) {
        composable(Route.LOGIN) {
            LoginScreen(navController, authViewModel)
        }
        composable(Route.MAIN) {
            MainNavUi(
                authViewModel = authViewModel,
                rootNavController = navController
            )
        }
    }
}