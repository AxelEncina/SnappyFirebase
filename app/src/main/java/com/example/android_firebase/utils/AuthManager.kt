package com.example.android_firebase.utils

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

sealed class AuthRes<out T> {
    data class Success<T>(val data: T) : AuthRes<T>()
    data class Error(val exception: Exception) : AuthRes<Nothing>()

}

class AuthManager {
    private val auth: FirebaseAuth by lazy { Firebase.auth }

    suspend fun signInAnonymously(): AuthRes<FirebaseUser> {

    }
}