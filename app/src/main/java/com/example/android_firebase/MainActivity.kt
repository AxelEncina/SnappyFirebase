package com.example.android_firebase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.android_firebase.navigation.Navigation
import com.example.android_firebase.ui.theme.Android_firebaseTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Android_firebaseTheme {
                Navigation(this)
            }
        }
    }
}

