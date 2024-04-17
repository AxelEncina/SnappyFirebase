package com.example.android_firebase.navigation

sealed class Routes(val route:String) {
    data object Login: Routes("Login Screen")

    data object Home: Routes("Home Screen")

    data object SignUp: Routes("SignUp Screen")

    data object ForgotPassword: Routes("ForgotPassword Screen")
}