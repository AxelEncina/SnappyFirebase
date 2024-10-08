package com.example.android_firebase.screens

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.ExitToApp
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.android_firebase.R
import com.example.android_firebase.navigation.Routes
import com.example.android_firebase.screens.db.ContactsScreen
import com.example.android_firebase.screens.db.NotesScreen
import com.example.android_firebase.ui.storage.CloudStorageScreen
import com.example.android_firebase.ui.theme.amarillo
import com.example.android_firebase.ui.theme.amarillo2
import com.example.android_firebase.ui.theme.fondo
import com.example.android_firebase.utils.AnalyticsManager
import com.example.android_firebase.utils.AuthManager
import com.example.android_firebase.utils.CloudStorageManager
import com.example.android_firebase.utils.FirestoreManager
import com.example.android_firebase.utils.RealtimeManager


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(analytics: AnalyticsManager, auth: AuthManager, navigation: NavController) {
    analytics.logScreenView(screenName = Routes.Home.route)
    val navController = rememberNavController()

    val user = auth.getCurrentUser()

    var showDialog by remember { mutableStateOf(false) }

    val context = LocalContext.current

    val onLogoutConfirmed: () -> Unit = {
        auth.signOut()
        navigation.navigate(Routes.Login.route) {
            popUpTo(Routes.Home.route) {
                inclusive = true
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = fondo,
                ),
                title = {
                    Row(
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (user?.photoUrl != null) {
                            AsyncImage(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(user.photoUrl)
                                    .crossfade(true)
                                    .build(),
                                contentDescription = "Foto de perfil",
                                placeholder = painterResource(id = R.drawable.profile),
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    //.padding(end = 8.dp)
                                    .size(40.dp)
                                    .clip(CircleShape))
                        } else {
                            Image(
                                painter = painterResource(R.drawable.profile),
                                contentDescription = "Foto de perfil por defecto",
                                modifier = Modifier
                                    .padding(end = 8.dp)
                                    .size(40.dp)
                                    .clip(CircleShape)
                            )
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                text = if (!user?.displayName.isNullOrEmpty()) "Hola ${user?.displayName}" else "Bienvenidx",
                                fontSize = 20.sp,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                color = amarillo2
                            )
                            Text(
                                text = if (!user?.email.isNullOrEmpty()) "${user?.email}" else "Anónimo",
                                fontSize = 12.sp,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                color = amarillo2
                            )
                        }
                    }
                },
                //colors = TopAppBarDefaults.smallTopAppBarColors(),
                actions = {
                    IconButton(
                        onClick = {
                            showDialog = true

                        }
                    ) {
                        Icon(Icons.Outlined.ExitToApp, contentDescription = "Cerrar sesión", tint = amarillo2)
                    }
                }
            )
        },
        bottomBar = {
            BottomBar(navController = navController)
        }
    ) { contentPadding ->
        Box(modifier = Modifier.padding(contentPadding)) {
            if (showDialog) {
                LogoutDialog(onConfirmLogout = {
                    onLogoutConfirmed()
                    showDialog = false
                }, onDismiss = { showDialog = false })
            }
            BottomNavGraph(navController = navController, context = context, authManager = auth)
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
                Text("Aceptar", color = amarillo)
            }
        },
        dismissButton = {
            Button(
                onClick = onDismiss
            ) {
                Text("Cancelar", color = amarillo)
            }
        }
    )
}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomNavScreen.Contact,
        BottomNavScreen.Note,
        BottomNavScreen.Photos
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
        label = { Text(text = screens.title ) },
        icon = { Icon(imageVector = screens.icon, contentDescription = "Icons", tint = amarillo2) },
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
fun BottomNavGraph(navController: NavHostController, context: Context, authManager: AuthManager) {
    val realtime = RealtimeManager(context)
    val firestore = FirestoreManager(context)
    val storage = CloudStorageManager(context)
    NavHost(navController = navController, startDestination = BottomNavScreen.Contact.route) {
        composable(route = BottomNavScreen.Contact.route) {
            ContactsScreen(realtime = realtime, authManager = authManager)
        }
        composable(route = BottomNavScreen.Note.route) {
            NotesScreen(firestore = firestore)
        }
        composable(route = BottomNavScreen.Photos.route) {
            CloudStorageScreen(storage = storage)
        }
    }
}

sealed class BottomNavScreen(val route: String, val title: String, val icon: ImageVector) {
    data object Photos : BottomNavScreen(
        route = "photos",
        title = "Photos",
        icon = Icons.Default.Face
    )

    data object Contact : BottomNavScreen(
        route = "contact",
        title = "Contactos",
        icon = Icons.Default.Person
    )

    data object Note : BottomNavScreen(
        route = "notes",
        title = "Notas",
        icon = Icons.Default.List
    )
}