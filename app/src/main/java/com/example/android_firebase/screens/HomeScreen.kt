package com.example.android_firebase.screens

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.android_firebase.R
import com.example.android_firebase.navigation.Routes
import com.example.android_firebase.ui.theme.amarillo
import com.example.android_firebase.ui.theme.celeste
import com.example.android_firebase.ui.theme.fondo
import com.example.android_firebase.viewmodel.HuntViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController, huntViewModel: HuntViewModel) {

    val lazyDog = FontFamily(
        Font(R.font.lazy_dog)
    )

    var presses by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = fondo,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(color = amarillo)) {
                                append("SNAPPY")
                            }
                            withStyle(style = SpanStyle(color = celeste)) {
                                append("AI")
                            }
                        },
                        style = TextStyle(fontSize = 40.sp, fontFamily = lazyDog),
                        modifier = Modifier.padding(100.dp, 0.dp, 0.dp, 0.dp)
                    )
                }
            )
        },
        bottomBar = {
            BottomBar(navController)
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate(Routes.Pending.route) },
                containerColor = fondo,
                ) {
                Icon(Icons.Default.Add, contentDescription = "Add", tint = amarillo)
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(50.dp))
            Image(
                painter = painterResource(id = R.drawable.logo2),
                contentDescription = "Top Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)

            )

            Spacer(modifier = Modifier.height(32.dp))


            Spacer(modifier = Modifier.height(15.dp))

            Text(
                text = "BIENVENIDO!",
                style = TextStyle(fontSize = 40.sp, fontFamily = lazyDog),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center

            )

            Spacer(modifier = Modifier.height(60.dp))

            Text(
                text = "Desea contestar una pregunta? 🤯",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontSize = 32.sp,
                    fontWeight = MaterialTheme.typography.bodyLarge.fontWeight),
                textAlign = TextAlign.Center
            )

            //Spacer(modifier = Modifier.weight(1f))
            //BottomNavGraph(navController = navController)
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
*/
@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomNavScreen.Question,
        BottomNavScreen.Notes,
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
fun BottomNavGraph(navController: NavHostController, context: Context) {
    NavHost(navController = navController, startDestination = BottomNavScreen.Notes.route) {
        composable(route = BottomNavScreen.Question.route) {
            NotesScreen()
        }
        composable(route = BottomNavScreen.Response.route) {
            NotesScreen()
        }
    }
}

sealed class BottomNavScreen(val route: String, val title: String, val icon: ImageVector) {
    object Notes : BottomNavScreen(
        route = "notes",
        title = "Notes",
        icon = Icons.Default.Home
    )

    object Question : BottomNavScreen(
        route = "questions",
        title = "Questions",
        icon = Icons.Default.Send
    )

    object Response : BottomNavScreen(
        route = "response",
        title = "Answers",
        icon = Icons.Default.Done
    )
}