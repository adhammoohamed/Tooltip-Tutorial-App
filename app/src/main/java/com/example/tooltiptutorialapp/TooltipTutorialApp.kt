package com.example.tooltiptutorialapp

import android.app.Application
import com.example.tooltiptutorialapp.util.shared_prefs.SharedPreferencesManager

class TooltipTutorialApp : Application() {
    lateinit var sharedPreferencesManager: SharedPreferencesManager

    override fun onCreate() {
        super.onCreate()
        sharedPreferencesManager = SharedPreferencesManager(this)
    }
}