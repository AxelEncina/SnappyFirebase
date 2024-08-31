package com.example.android_firebase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph
import com.example.android_firebase.navigation.NavGraph
import com.example.android_firebase.ui.theme.Android_firebaseTheme
import com.example.android_firebase.viewmodel.HuntViewModel
import com.example.android_firebase.viewmodel.PredictionViewModel

class MainActivity : ComponentActivity() {
    private val huntViewModel: HuntViewModel by viewModels()
    private val predictionViewModel: PredictionViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavGraph(huntViewModel = huntViewModel, predictionViewModel = predictionViewModel) }
    }
}



