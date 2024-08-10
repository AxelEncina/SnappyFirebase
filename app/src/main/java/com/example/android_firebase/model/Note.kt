package com.example.android_firebase.model

data class Note(
    var id: String? = null,
    var userId: String = "",
    val title: String = "",
    val content: String = ""
)