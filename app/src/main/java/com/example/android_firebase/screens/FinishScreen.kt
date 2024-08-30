package com.example.android_firebase.screens


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.android_firebase.navigation.Routes
import com.example.android_firebase.viewmodel.HuntViewModel


@Composable
fun HuntFinishScreen(navController: NavHostController, huntViewModel: HuntViewModel) {
    //HandleBackPressToHome(navController, huntViewModel)

    val score = huntViewModel.score.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "🤠", style = MaterialTheme.typography.displayLarge.copy(
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 65.sp))

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Text(
                    text = "The hunt is over!",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Your score: ${score.value}",
                    style = MaterialTheme.typography.displaySmall.copy(
                        fontWeight = FontWeight.W600)
                )
            }

            Spacer(modifier = Modifier.height(50.dp))
        }

        Button(
            onClick = {
                navController.navigate(Routes.Home.route) {
                    popUpTo(Routes.Home.route) { inclusive = true }
                }
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 35.dp)
        ) {
            Text(text = "Play again")
        }
    }
}