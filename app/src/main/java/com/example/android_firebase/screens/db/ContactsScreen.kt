package com.example.android_firebase.screens.db

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.android_firebase.model.Contact
import com.example.android_firebase.ui.theme.amarillo
import com.example.android_firebase.ui.theme.amarillo2
import com.example.android_firebase.ui.theme.celeste
import com.example.android_firebase.ui.theme.fondo
import com.example.android_firebase.ui.theme.letra
import com.example.android_firebase.utils.AuthManager
import com.example.android_firebase.utils.RealtimeManager

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ContactsScreen(realtime: RealtimeManager, authManager: AuthManager) {
    var showAddContactDialog by remember { mutableStateOf(false) }

    val contacts by realtime.getContactsFlow().collectAsState(emptyList())

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                containerColor = fondo,
                onClick = {
                    showAddContactDialog = true
                },
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Agregar una Nota", tint = amarillo)
            }

            if (showAddContactDialog) {
                AddContactDialog(
                    onContactAdded = { contact ->
                        realtime.addContact(contact)
                        showAddContactDialog = false
                    },
                    onDialogDismissed = { showAddContactDialog = false },
                    authManager = authManager,
                )
            }
        }
    ) { _  ->
        if(!contacts.isNullOrEmpty()) {
            LazyColumn {
                contacts.forEach { contact ->
                    item {
                        ContactItem(contact = contact, realtime = realtime)
                    }
                }
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(imageVector = Icons.Default.Person, contentDescription = null, modifier = Modifier.size(100.dp))
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "No se encontraron \nContactos",
                    fontSize = 18.sp, fontWeight = FontWeight.Thin, textAlign = TextAlign.Center)
            }
        }
    }
}

@Composable
fun ContactItem(contact: Contact, realtime: RealtimeManager) {
    var showDeleteContactDialog by remember { mutableStateOf(false) }

    val onDeleteContactConfirmed: () -> Unit = {
        realtime.deleteContact(contact.key ?: "")
    }

    if (showDeleteContactDialog) {
        DeleteContactDialog(
            onConfirmDelete = {
                onDeleteContactConfirmed()
                showDeleteContactDialog = false
            },
            onDismiss = {
                showDeleteContactDialog = false
            }
        )
    }

    Card(
        modifier = Modifier
            .padding(start = 15.dp, end = 15.dp, top = 15.dp, bottom = 0.dp)
            .fillMaxWidth())
    {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(modifier = Modifier.weight(3f)) {
                Text(
                    text = contact.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis)
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = contact.phoneNumber,
                    fontWeight = FontWeight.Medium,
                    fontSize = 15.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis)
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = contact.email,
                    fontWeight = FontWeight.Thin,
                    fontSize = 12.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis)
            }
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.Center,
            ) {
                IconButton(
                    onClick = {
                        showDeleteContactDialog = true
                    },
                ) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete Icon")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddContactDialog(onContactAdded: (Contact) -> Unit, onDialogDismissed: () -> Unit, authManager: AuthManager) {
    var name by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var uid = authManager.getCurrentUser()?.uid

    AlertDialog(
        onDismissRequest = {},
        title = { Text(text = "Agregar Contacto") },
        confirmButton = {
            Button(
                onClick = {
                    val newContact = Contact(
                        name = name,
                        phoneNumber = phoneNumber,
                        email = email,
                        uid = uid.toString())
                    onContactAdded(newContact)
                    name = ""
                    phoneNumber = ""
                    email = ""
                }
            ) {
                Text(text = "Agregar", color = amarillo)
            }
        },
        dismissButton = {
            Button(
                onClick = {
                    onDialogDismissed()
                }
            ) {
                Text(text = "Cancelar", color = amarillo)
            }
        },
        text = {
            Column {
                OutlinedTextField(
                    label = { Text(text = "Nombre", color = fondo) },
                    value = name,
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent,
                        focusedIndicatorColor = celeste,
                        unfocusedIndicatorColor = amarillo,
                        cursorColor = Color.Yellow
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    onValueChange = { name = it }
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = phoneNumber,
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent,
                        focusedIndicatorColor = celeste,
                        unfocusedIndicatorColor = amarillo,
                        cursorColor = Color.Yellow
                    ),
                    onValueChange = { phoneNumber = it },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone),
                    label = { Text(text = "Teléfono", color = fondo) },
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    label = { Text(text = "Correo electrónico", color = fondo) },
                    value = email,
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent,
                        focusedIndicatorColor = celeste,
                        unfocusedIndicatorColor = amarillo,
                        cursorColor = Color.Yellow
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    onValueChange = { email = it }
                )
            }
        }
    )
}

@Composable
fun DeleteContactDialog(onConfirmDelete: () -> Unit, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Eliminar contacto") },
        text = { Text("¿Estás seguro que deseas eliminar el contacto?") },
        confirmButton = {
            Button(
                onClick = onConfirmDelete
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