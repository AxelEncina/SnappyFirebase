package com.example.android_firebase.navigation

sealed class Routes(val route:String) {

    data object Home: Routes("Home Screen")

    data object ItemValidating : Routes("itemValidating")

    data object ItemValidationSuccess : Routes("itemValidationSuccess")

    data object ItemValidationFailure : Routes("itemValidationFailure")

    data object Pending : Routes("pending")

    data object Finish : Routes("finish")

}