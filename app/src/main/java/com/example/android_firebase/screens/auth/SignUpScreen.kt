package com.example.android_firebase.screens.auth

import android.content.Context
import android.os.Bundle
import android.widget.Toast
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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.android_firebase.navigation.Routes
import com.example.android_firebase.ui.theme.Purple40
import com.example.android_firebase.ui.theme.amarillo
import com.example.android_firebase.ui.theme.amarillo2
import com.example.android_firebase.ui.theme.letra
import com.example.android_firebase.utils.AnalyticsManager
import com.example.android_firebase.utils.AuthManager
import com.example.android_firebase.utils.AuthRes
import com.google.firebase.analytics.FirebaseAnalytics
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(analytics: AnalyticsManager, auth: AuthManager, navigation: NavController) {
    analytics.logScreenView(screenName = Routes.SignUp.route)

    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Crear una cuenta",
            style = TextStyle(fontSize = 40.sp, color = amarillo)
        )
        Spacer(modifier = Modifier.height(50.dp))
        OutlinedTextField(
            label = { Text(text = "Correo electrónico", color = letra) },
            value = email,
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent,
                focusedIndicatorColor = amarillo2,
                unfocusedIndicatorColor = amarillo,
                cursorColor = Color.Yellow
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            onValueChange = { email = it })

        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            label = { Text(text = "Contraseña", color = letra) },
            value = password,
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent,
                focusedIndicatorColor = amarillo2,
                unfocusedIndicatorColor = amarillo,
                cursorColor = Color.Yellow
            ),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            onValueChange = { password = it })

        Spacer(modifier = Modifier.height(30.dp))
        Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
            Button(
                onClick = {
                    scope.launch {
                        signUp(email, password, auth, analytics, context, navigation)
                    }
                },
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(amarillo2)
            ) {
                Text(text = "Registrarse", color = Color.Black)
            }
        }

        Spacer(modifier = Modifier.height(40.dp))
        ClickableText(
            text = AnnotatedString("¿Ya tienes una cuenta? Inicia sesión"),
            onClick = {
                navigation.popBackStack()
            },
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = FontFamily.Default,
                textDecoration = TextDecoration.Underline,
                color = amarillo2
            )
        )
    }
}

private suspend fun signUp(email: String, password: String, auth: AuthManager, analytics: AnalyticsManager, context: Context, navigation: NavController) {
    if (email.isNotEmpty() && password.isNotEmpty()) {
        when(val result = auth.createUserWithEmailAndPassword(email, password)) {
            is AuthRes.Success -> {
                analytics.logButtonClicked(FirebaseAnalytics.Event.SIGN_UP)
                Toast.makeText(context, "Usuario creado correctamente", Toast.LENGTH_SHORT).show()
                navigation.popBackStack()
            }
            is AuthRes.Error -> {
                analytics.logButtonClicked("Error al crear usuario ${result.errorMessage}")
                Toast.makeText(context, "Error al crear usuario ${result.errorMessage}", Toast.LENGTH_SHORT).show(
                )
            }
        }
    } else {
        Toast.makeText(context, "Email y contraseña no pueden estar vacíos", Toast.LENGTH_SHORT).show()
        analytics.logEvent("sign_up_error", Bundle().apply {
            putString("error", "Email y contraseña no pueden estar vacíos")
        })
    }
}


