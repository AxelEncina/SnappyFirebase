package com.example.android_firebase.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ExitToApp
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.android_firebase.navigation.Routes

@Composable
fun HomeScreen(analytics: AnalyticsManager, navigation: NavController) {
    analytics.logScreenView(screenName = Routes.Home.route)
    val navController = rememberNavController()
    
    var showDialog by remember { mutableStateOf(false) }
    
    val onLogoutConfirmed: () -> Unit = {
        
    }
    
    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                        Row(
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                        
                            Spacer(modifier = Modifier.width(10.dp))
                            Column {
                                Text(
                                    text = "Bienvenidos",
                                    fontSize = 20.sp,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                                Text(
                                    text = "Usuarios",
                                    fontSize = 12.sp,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }
                },
                colors = TopAppBarDefaults.topAppBarColors(),
                actions = {
                    IconButton(onClick = { showDialog = true }) {
                        Icon(Icons.Outlined.ExitToApp, contentDescription = "Logout")
                    }
                }
            )
        },
        bottomBar = {
            BottomBar(navController = navController)
        }
    ) { contentPadding ->
        Box(modifier = Modifier.padding(contentPadding)) {
            if(showDialog) {
                LogoutDialog(onConfirmLogout = {
                    onLogoutConfirmed()
                    showDialog = false
                }, onDismiss = { showDialog = false })
            }
            BottomNavGraph(navController = navController)
        }
    }
}

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
fun BottomBar(navController: NavController) {
    val screens = listOf(
        BottomNavScreen.Contact,
        BottomNavScreen.Note,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    NavigationBar {
        screens.forEach { screen ->
            val selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true
            IconButton(
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(Routes.Home.route) {
                            inclusive = true
                        }
                    }
                },
                selected = selected
            ) {
                Icon(screen.icon, contentDescription = screen.label)
            }
        }
    }
}