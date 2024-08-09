package com.example.android_firebase.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.android_firebase.ui.theme.Android_firebaseTheme
import com.example.android_firebase.ui.theme.fondo

@Composable
fun Screen(content: @Composable () -> Unit) {
    Android_firebaseTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = fondo
        ) {
            content()
        }
    }
}