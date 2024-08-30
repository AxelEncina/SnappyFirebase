package com.example.android_firebase.screens

import androidx.compose.foundation.Image
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.android_firebase.R
import com.example.android_firebase.navigation.Routes
import com.example.android_firebase.ui.theme.amarillo
import com.example.android_firebase.ui.theme.celeste
import com.example.android_firebase.viewmodel.HuntViewModel


@Composable
fun HomeScreen(navController: NavHostController, huntViewModel: HuntViewModel) {

    val lazyDog = FontFamily(
        Font(R.font.lazy_dog)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(25.dp))
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

        Spacer(modifier = Modifier.height(15.dp))

        Text(
            text = "BIENVENIDO! \n\n Desea contestar una pregunta?",
            style = MaterialTheme.typography.titleLarge.copy(
                fontSize = 32.sp,
                fontWeight = MaterialTheme.typography.bodyLarge.fontWeight),
            textAlign = androidx.compose.ui.text.style.TextAlign.Center
        )

        Spacer(modifier = Modifier.height(15.dp))

        Text(
            text = "🤯",
            style = MaterialTheme.typography.titleLarge.copy(
                fontSize = 32.sp,
                fontWeight = MaterialTheme.typography.bodyLarge.fontWeight)
        )

        Spacer(modifier = Modifier.weight(1f))

        OutlinedButton(
            onClick = {
                navController.navigate(Routes.Pending.route)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .border(1.dp, amarillo, shape = RoundedCornerShape(50.dp))
        ) {
            Text(text = "Contestar".uppercase(), color = amarillo)
        }
    }
}
/*
@Composable
fun LogoutDialog(onConfirmLogout: () -> Unit, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Cerrar sesión") },
        text = { Text("¿Estás seguro que deseas cerrar sesión?") },
        confirmButton = {
            Button(
                onClick = onConfirmLogout
            ) {
                Text("Aceptar")
            }
        },
        dismissButton = {
            Button(
                onClick = onDismiss
            ) {
                Text("Cancelar")
            }
        }
    )
}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomNavScreen.Question,
        BottomNavScreen.Response
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    NavigationBar {
        screens.forEach { screens ->
            if (currentDestination != null) {
                AddItem(
                    screens = screens,
                    currentDestination = currentDestination,
                    navController = navController
                )
            }
        }
    }
}

@Composable
fun RowScope.AddItem(
    screens: BottomNavScreen,
    currentDestination: NavDestination,
    navController: NavHostController
) {
    NavigationBarItem(
        label = { Text(text = screens.title) },
        icon = { Icon(imageVector = screens.icon, contentDescription = "Icons") },
        selected = currentDestination.hierarchy?.any {
            it.route == screens.route
        } == true,
        onClick = {
            navController.navigate(screens.route) {
                popUpTo(navController.graph.id)
                launchSingleTop = true
            }
        }
    )
}

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = BottomNavScreen.Question.route) {
        composable(route = BottomNavScreen.Question.route) {
            QuestionScreen()
        }
        composable(route = BottomNavScreen.Response.route) {
            ResponseScreen()
        }
    }
}

sealed class BottomNavScreen(val route: String, val title: String, val icon: ImageVector) {
    object Question : BottomNavScreen(
        route = "questions",
        title = "Questions",
        icon = Icons.Default.Send
    )

    object Response : BottomNavScreen(
        route = "response",
        title = "Response",
        icon = Icons.Default.Done
    )
}*/