package com.example.android_firebase.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.android_firebase.screens.HomeScreen
import com.example.android_firebase.screens.HuntFinishScreen
import com.example.android_firebase.validation.HuntItemPendingScreen
import com.example.android_firebase.validation.ItemValidatingScreen
import com.example.android_firebase.validation.ItemValidationFailureScreen
import com.example.android_firebase.validation.ItemValidationSuccessScreen
import com.example.android_firebase.viewmodel.HuntViewModel
import com.example.android_firebase.viewmodel.PredictionViewModel

@Composable
fun NavGraph(huntViewModel: HuntViewModel, predictionViewModel: PredictionViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.Home.route
    ) {
        composable(Routes.Home.route) {
            HomeScreen(
                navController = navController, huntViewModel
            )
        }
        composable(Routes.ItemValidating.route) {
            ItemValidatingScreen(
                navController = navController,
                huntViewModel = huntViewModel,
                predictionViewModel = predictionViewModel
            )
        }
        composable(Routes.ItemValidationSuccess.route) {
            ItemValidationSuccessScreen(
                navController = navController,
                huntViewModel = huntViewModel,
                predictionViewModel = predictionViewModel
            )
        }
        composable(Routes.ItemValidationFailure.route) {
            ItemValidationFailureScreen(
                navController = navController,
                huntViewModel = huntViewModel,
                predictionViewModel = predictionViewModel
            )
        }
        composable(Routes.Pending.route) {
            HuntItemPendingScreen(
                navController = navController,
                huntViewModel = huntViewModel,
                predictionViewModel = predictionViewModel)
        }
        composable(Routes.Finish.route) {
            HuntFinishScreen(
                navController = navController,
                huntViewModel = huntViewModel)
        }

    }
}

