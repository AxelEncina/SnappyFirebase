package com.example.android_firebase.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.android_firebase.screens.HomeScreen
import com.example.android_firebase.screens.Screen
import com.example.android_firebase.screens.auth.ForgotPasswordScreen
import com.example.android_firebase.screens.auth.LoginScreen
import com.example.android_firebase.screens.auth.SignUpScreen
import com.example.android_firebase.utils.AnalyticsManager
import com.example.android_firebase.utils.AuthManager
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.logEvent
import com.google.firebase.auth.FirebaseUser


@Composable
fun Navigation(context: Context, navController: NavHostController = rememberNavController()) {
    val analytics: AnalyticsManager = AnalyticsManager(context)
    val authManager: AuthManager = AuthManager(context)


    val user: FirebaseUser? = authManager.getCurrentUser()

    Screen {
        NavHost(
            navController = navController,
            startDestination = if(user == null) Routes.Login.route else Routes.Home.route
        ) {
            composable(Routes.Login.route) {
                LoginScreen(
                    analytics = analytics,
                    auth = authManager,
                    navigation = navController,
                    )
            }

            composable(Routes.Home.route) {
                HomeScreen(
                    analytics = analytics,
                    auth = authManager,
                    navigation = navController,
                )
            }

            composable(Routes.SignUp.route) {
                SignUpScreen(
                    analytics = analytics,
                    auth = authManager,
                    navigation = navController,
                )
            }

            composable(Routes.ForgotPassword.route) {
                ForgotPasswordScreen(
                    analytics = analytics,
                    auth = authManager,
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