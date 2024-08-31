package com.example.android_firebase.validation

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.android_firebase.navigation.Routes
import com.example.android_firebase.viewmodel.HuntViewModel
import com.example.android_firebase.viewmodel.PredictionViewModel
import okhttp3.Route

@Composable
fun ItemValidatingScreen(navController: NavHostController,
                         huntViewModel: HuntViewModel,
                         predictionViewModel: PredictionViewModel
) {

    //HandleBackPressToHome(navController, huntViewModel)

    val predictedName = predictionViewModel.predictedName.collectAsState()

    LaunchedEffect(predictedName.value) {
        if (predictedName.value != null) {
            Log.d("ItemValidatingScreen", "Predicted name does not match the current item: ${predictedName.value} - ${predictedName.value?.trim()?.lowercase() == huntViewModel.currentItem.value?.trim()?.lowercase()} ${huntViewModel.currentItem.value}")
            if (predictedName.value.equals(huntViewModel.word, ignoreCase = true)) {
                predictionViewModel.resetRetryCount()
                navController.navigate(Routes.ItemValidationSuccess.route)
            } else {
                navController.navigate(Routes.ItemValidationFailure.route)
                Log.d("ItemValidatingScreen", "Predicted name does not match the current item")
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(15.dp))

        //HuntProgress(huntViewModel)

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Text(text = "🤖", style = MaterialTheme.typography.displayLarge)

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "La IA está validando tu respuesta...",
                style = MaterialTheme.typography.titleLarge)
        }

        Box {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 35.dp)
            )
        }

    }
}