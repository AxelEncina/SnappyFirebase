package com.example.android_firebase.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.example.android_firebase.navigation.trackScreen
import com.google.firebase.analytics.FirebaseAnalytics

@Composable
fun Home(analytics: FirebaseAnalytics) {

    trackScreen(name = "Ingreso a HomeScreen", analytics = analytics)

    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                "Home",
                fontSize = 40.sp
            )
        }
}