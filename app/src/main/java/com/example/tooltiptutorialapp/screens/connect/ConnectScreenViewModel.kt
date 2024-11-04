package com.example.tooltiptutorialapp.screens.connect

import androidx.lifecycle.ViewModel
import com.example.tooltiptutorialapp.util.utility_model.populateStudyPartnerList

class ConnectScreenViewModel : ViewModel() {

    init {
        populateStudyPartnerList()
    }
}