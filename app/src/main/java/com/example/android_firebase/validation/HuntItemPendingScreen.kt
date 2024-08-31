package com.example.android_firebase.validation


import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.android_firebase.R
import com.example.android_firebase.navigation.Routes
import com.example.android_firebase.ui.theme.amarillo
import com.example.android_firebase.ui.theme.celeste
import com.example.android_firebase.viewmodel.HuntViewModel
import com.example.android_firebase.viewmodel.PredictionViewModel

@Composable
fun HuntItemPendingScreen(navController: NavHostController,
                          huntViewModel: HuntViewModel,
                          predictionViewModel: PredictionViewModel
) {

    val lazyDog = FontFamily(
        Font(R.font.lazy_dog)
    )
    //HandleBackPressToHome(navController, huntViewModel)

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview()
    ) { bitmap ->
        predictionViewModel.setCapturedImage(bitmap)
        predictionViewModel.predictImageName()
        navController.navigate(Routes.ItemValidating.route)
    }

    val currentItem = huntViewModel.currentItem.collectAsState()

    LaunchedEffect(huntViewModel) {
        huntViewModel.onFinished = {
            navController.navigate(Routes.Finish.route)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(15.dp))

        Image(
            painter = painterResource(id = R.drawable.logo2),
            contentDescription = "Top Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = amarillo)) {
                    append("SNAPPY")
                }
                withStyle(style = SpanStyle(color = celeste)) {
                    append("AI")
                }
            },
            style = TextStyle(fontSize = 60.sp, fontFamily = lazyDog)
        )
        //HuntProgress(huntViewModel)
        Spacer(modifier = Modifier.height(32.dp))
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Text(
                text = "QUE ES UNA...",
                style = MaterialTheme.typography.titleLarge.copy(fontSize = 35.sp, fontWeight = FontWeight.Medium)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "TABLE?",
                style = MaterialTheme.typography.displayLarge.copy(
                    fontSize = 60.sp,
                    fontWeight = FontWeight.W800)
            )
        }

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            OutlinedButton(onClick = { //back home
                navController.popBackStack()
            }, modifier = Modifier.
                border(0.dp,amarillo, shape = RoundedCornerShape(50.dp)).padding(0.dp,0.dp,20.dp,0.dp)
                ) {
                Text(text = "Volver", color = amarillo)
            }

            Spacer(modifier = Modifier.width(8.dp))

            Button(onClick = { launcher.launch(null) },

                colors = ButtonDefaults.buttonColors(
                    containerColor = celeste,
                ),
                modifier = Modifier.border(0.dp, celeste, shape = RoundedCornerShape(50.dp))
                ) {
                Text(text = "Tomar Foto", color = amarillo)
            }
        }

        Spacer(modifier = Modifier.height(35.dp))

    }
}