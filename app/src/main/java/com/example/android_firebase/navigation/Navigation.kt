package com.example.android_firebase.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.android_firebase.screens.Screen
import com.example.android_firebase.screens.home.Home
import com.example.android_firebase.screens.login.Login
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.logEvent


@Composable
fun Navigation(context: Context, navController: NavHostController = rememberNavController()) {
    var analytics: AnalyticsManager = AnalyticsManager(context)

    Screen {
        NavHost(
            navController = navController,
            startDestination = Routes.Login.route
        ) {
            composable(Routes.Login.route) {
                LoginScreen(
                    analytics = analytics,
                    navigation = navController,
                    )
            }

            composable(Routes.Home.route) {
                HomeScreen(
                    analytics = analytics,
                    navigation = navController,
                )
            }

            composable(Routes.SignUp.route) {
                SignUpScreen(
                    analytics = analytics,
                    navigation = navController,
                )
            }

            composable(Routes.ForgotPassword.route) {
                ForgotPasswordScreen(
                    analytics = analytics,
                    navigation = navController,
                )

            }
        }
    }
}

@Composable //para que no se llame cada vez que se renderiza
fun trackScreen(name: String, analytics: FirebaseAnalytics) {
    DisposableEffect(Unit) {
        onDispose {
            analytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW) {
                param(FirebaseAnalytics.Param.SCREEN_NAME, name)
            }
        }
    }
}