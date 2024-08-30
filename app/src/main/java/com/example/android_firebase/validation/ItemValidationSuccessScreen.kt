package com.example.android_firebase.validation


import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.android_firebase.navigation.Routes
import com.example.android_firebase.ui.theme.amarillo
import com.example.android_firebase.viewmodel.HuntViewModel
import com.example.android_firebase.viewmodel.PredictionViewModel

@Composable
fun ItemValidationSuccessScreen(navController: NavHostController,
                                huntViewModel: HuntViewModel,
                                predictionViewModel: PredictionViewModel
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(15.dp))



        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Text(text = "🤩", style = MaterialTheme.typography.displayLarge)

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "CORRECTO",
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedButton(onClick = { //back home
                navController.navigate(Routes.Home.route)
            }, modifier = Modifier.
            border(0.dp, amarillo, shape = RoundedCornerShape(50.dp))
            ) {
                Text(text = "INICIO", color = amarillo)
            }

        }
    }
}
