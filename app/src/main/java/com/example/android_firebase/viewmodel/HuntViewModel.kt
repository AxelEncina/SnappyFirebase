package com.example.android_firebase.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.BuildConfig
import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class HuntViewModel : ViewModel() {
    private val _selectedLocation = MutableStateFlow<String?>(null)
    val selectedLocation: StateFlow<String?> = _selectedLocation

    private val _currentItems = MutableStateFlow<List<String>>(emptyList())
    val currentItems: StateFlow<List<String>> = _currentItems

    private val _currentItem = MutableStateFlow<String?>(null)
    val currentItem: StateFlow<String?> = _currentItem

    private val _itemsLeft = MutableStateFlow(0)
    val itemsLeft: StateFlow<Int> = _itemsLeft

    private val _score = MutableStateFlow(0)
    val score: StateFlow<Int> = _score

    private var currentIndex = 0
    var onFinished: (() -> Unit)? = null

    val palabra: String = "Table"
}