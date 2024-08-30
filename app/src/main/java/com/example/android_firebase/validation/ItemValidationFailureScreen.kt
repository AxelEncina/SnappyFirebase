package com.example.android_firebase.validation


import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.android_firebase.navigation.Routes
import com.example.android_firebase.ui.theme.celeste
import com.example.android_firebase.viewmodel.HuntViewModel
import com.example.android_firebase.viewmodel.PredictionViewModel
import okhttp3.Route


@Composable
fun ItemValidationFailureScreen(
    navController: NavHostController,
    huntViewModel: HuntViewModel,
    predictionViewModel: PredictionViewModel
) {

    //HandleBackPressToHome(navController, huntViewModel)

    val retryCount = remember { mutableStateOf(predictionViewModel.shouldRetry()) }

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
            Text(text = "🚨", style = MaterialTheme.typography.displayLarge)

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = if (retryCount.value) {
                    "RESPUESTA INCORRECTA"
                } else {
                    "Cantidad de Intentos Agotada\n\n Gracias por Participar"
                },
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.W500),
                textAlign = TextAlign.Center
            )
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Button(
                onClick = {
                    if (retryCount.value) {
                        predictionViewModel.incrementRetryCount()
                        predictionViewModel.resetPrediction()
                        navController.navigate(Routes.Pending.route)
                    } else {
                        predictionViewModel.resetRetryCount()
                        navController.navigate(Routes.Home.route)
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = celeste,
                ),
                modifier = Modifier.border(0.dp, celeste, shape = RoundedCornerShape(50.dp))
            )
            {
                Text(text = if (retryCount.value) "Vuelve a Intentarlo" else "INICIO")
            }
        }


        Spacer(modifier = Modifier.height(35.dp))
    }
}
