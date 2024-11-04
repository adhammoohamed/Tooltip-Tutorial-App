package com.example.tooltiptutorialapp.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class HomeScreenViewModel : ViewModel() {
    var isOverlayVisible by mutableStateOf(true)
        private set

    fun hideOverlay() {
        isOverlayVisible = false
    }

}