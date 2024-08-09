package com.example.android_firebase.screens.auth

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.ExitToApp
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedIconButton
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.android_firebase.R
import com.example.android_firebase.navigation.Routes
import com.example.android_firebase.ui.theme.Purple40
import com.example.android_firebase.ui.theme.amarillo
import com.example.android_firebase.ui.theme.amarillo2
import com.example.android_firebase.ui.theme.letra
import com.example.android_firebase.utils.AnalyticsManager
import com.example.android_firebase.utils.AuthManager
import com.example.android_firebase.utils.AuthRes
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotPasswordScreen(analytics: AnalyticsManager, auth: AuthManager, navigation: NavController) {
    analytics.logScreenView(screenName = Routes.ForgotPassword.route)

    val context = LocalContext.current
    var email by remember { mutableStateOf("") }

    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize().padding(top = 50.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //boton para volver atras de color amarillo y que este ubicado en la esquina de arriba a la izquierda
        OutlinedIconButton(
            onClick = {
                navigation.navigate(Routes.Login.route)
            },
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .background(Color.Transparent)
                .border(1.dp, amarillo, CircleShape)
        ) {
            Icon(
                imageVector = Icons.Outlined.ArrowBack,
                contentDescription = "Back",
                tint = amarillo
            )
        }

        Text(
            modifier = Modifier.padding(0.dp, 20.dp, 0.dp, 0.dp),
            text = "Olvidó su Contraseña?",
            style = TextStyle(fontSize = 30.sp, color = amarillo)
        )
        Spacer(modifier = Modifier.height(50.dp))
        Text(
            modifier = Modifier.padding(20.dp, 20.dp, 20.dp, 0.dp),
            text = "Ingrese su correo electrónico, le enviaremos un enlace para restablecer su contraseña.",
            style = TextStyle(fontSize = 20.sp, color = amarillo2,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center)
        )
        Spacer(modifier = Modifier.height(20.dp))
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

        Spacer(modifier = Modifier.height(30.dp))
        Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
            Button(
                onClick = {
                    scope.launch {
                        when(val res = auth.resetPassword(email)) {
                            is AuthRes.Success -> {
                                analytics.logButtonClicked(buttonName = "Reset password $email")
                                Toast.makeText(context, "Correo enviado", Toast.LENGTH_SHORT).show()
                                navigation.navigate(Routes.Login.route)
                            }
                            is AuthRes.Error -> {
                                analytics.logError(error = "Reset password error $email : ${res.errorMessage}")
                                Toast.makeText(context, "Error al enviar el correo", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                },
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .width(280.dp)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(amarillo2)
            ) {
                Text(text = "Recuperar Contraseña", color = Color.Black)
            }
        }
    }
}
