package com.example.android_firebase.screens.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.android_firebase.navigation.Routes
import com.example.android_firebase.ui.theme.Purple40

@Composable
fun SignUpScreen(analytics: AnalyticsManager, navigation: NavController) {
    analytics.logScreenView(screenName = Routes.SignUp.route)

    val context = LocalContext.current
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Crear cuenta",
            style = TextStyle(
                fontSize = 40.sp,
                color = Purple40
            )
        )
        Spacer(modifier = Modifier.height(50.dp))
        TextField(
            label = { Text(text = "Email") },
            value = email,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            onValueChange = { email = it })

        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            label = { Text(text = "Contraseña") },
            value = password,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            onValueChange = { password = it })

        Spacer(modifier = Modifier.height(20.dp))
        Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
            Button(
                onClick = {

                },
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
            ) {
                Text(text ="Regístrate")
            }
        }
        
        Spacer(modifier = Modifier.height(40.dp))
        ClickableText(
            text = AnnotatedString("¿Ya tienes cuenta? Inicia sesión"),
            onClick = {
                navigation.popBackStack()
            },
            style = TextStyle(
                color = Purple40,
                fontSize = 14.sp,
                fontFamily = FontFamily.Default,
                textDecoration = TextDecoration.Underline,
            )
        )

    }

}


