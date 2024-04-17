package com.example.android_firebase.screens.auth

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScopeInstance.align
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.android_firebase.R
import com.example.android_firebase.navigation.Routes
import com.example.android_firebase.ui.theme.Purple40

@Composable
fun LoginScreen(analytics: AnalyticsManager, navigation: NavController) {
    analytics.logScreenView(screenName = Routes.Login.route)

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize()) {
        ClickableText(
            text = AnnotatedString("¿No tienes una cuenta? Regístrate aquí"),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(50.dp),
            onClick = {
              navigation.navigate(Routes.SignUp.route)
              analytics.logButtonClicked(
                  buttonName = "No tienes cuenta",
              )
            },
            style = TextStyle(
                color = Purple40,
                fontSize = 16.sp,
                fontFamily = FontFamily.Default,
                textDecoration = TextDecoration.Underline,
            )
        )
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = "Logo",
            modifier = Modifier.size(100.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "TITULO",
            style = TextStyle(fontSize = 30.sp))
        Spacer(modifier = Modifier.height(30.dp))
        TextField(
            label = { Text(text = "Email") },
            value = email,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            onValueChange = { email = it })
        Spacer(modifier = Modifier.height(10.dp))
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
                    .height(50.dp)
            ) {
                Text(text = "Iniciar sesión".uppercase()
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        ClickableText(
            text = AnnotatedString("¿Olvidaste tu contraseña?"),
            onClick = {
                navigation.navigate(Routes.ForgotPassword.route) {
                    popUpTo(Routes.Login.route) {
                        inclusive = true
                    }
                }
                analytics.logButtonClicked("Click: Olvidaste tu contraseña",)
            },
            style = TextStyle(
                color = Purple40,
                fontSize = 14.sp,
                fontFamily = FontFamily.Default,
                textDecoration = TextDecoration.Underline,
            )
        )
        Spacer(modifier = Modifier.height(25.dp))
        Text(text = "------------ 0 ------------", style = TextStyle(color = Color.Green))
        Spacer(modifier = Modifier.height(25.dp))
        SocialMediaButton(
            onClick = {

            },
            text = "Continuar como invitado",
            icon = R.drawable.ic_incognito,
            color = Color (0xFF363636),
        )
    }
}

@Composable
fun SocialMediaButton(
    onClick: () -> Unit,
    text: String,
    icon: Int,
    color: Color
) {
    var click by remember { mutableStateOf(false) }

    Surface(
        color = color,
        shape = RoundedCornerShape(50.dp),
        modifier = Modifier
            .padding(start = 40.dp, end = 40.dp)
            .clickable { click = !click },
        onClick = onClick,
        border = BorderStroke(width = 1.dp, color = if(icon == R.drawable.ic_incognito) Color(0xFFE8E8E8) else Color.Transparent)
    ) {
        Row(
            modifier = Modifier.padding(start = 12.dp, end = 12.dp, top = 8.dp, bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
          Icon(
              painter = painterResource(id = icon),
              modifier = Modifier.size(24.dp),
              contentDescription = text,
              tint = Color.Unspecified
          )
          Spacer(modifier = Modifier.width(10.dp))
          Text(text = "$text", color = if (icon == R.drawable.ic_incognito) Color(0xFF363636) else Color.White)
            click = true
        }
    }
}
