package com.example.tooltiptutorialapp.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.tooltiptutorialapp.util.shared_prefs.SharedPreferencesManager
import com.example.tooltiptutorialapp.util.utility_model.populateUnitList

class HomeScreenViewModel(private val sharedPreferencesManager: SharedPreferencesManager) : ViewModel() {
    var isOverlayVisible by mutableStateOf(true)
        private set


    init {
        populateUnitList()
        checkFirstLaunch()
    }

    private fun checkFirstLaunch() {
        if (!sharedPreferencesManager.isFirstLaunch) {
            isOverlayVisible = false
        }
        sharedPreferencesManager.isFirstLaunch = false
    }
    fun hideOverlay() {
        isOverlayVisible = false
    }

}