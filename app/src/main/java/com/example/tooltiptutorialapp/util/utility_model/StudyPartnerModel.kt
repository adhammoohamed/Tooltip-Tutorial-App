package com.example.tooltiptutorialapp.util.utility_model

data class StudyPartnerModel(
    val name: String,
    val gender: String,
    val age: Int,
    val location: String,
    val joinedDate: String,
    val targetingLevel: String,
    val lastSeen: String,
    val spokenLanguages: List<String>,
)


val studyPartnerList = mutableListOf<StudyPartnerModel>()

fun populateStudyPartnerList() {
    (1..10).forEach { _ ->
        studyPartnerList.add(
            StudyPartnerModel(
                name = "Reem Sayed",
                lastSeen = "Yesterday",
                spokenLanguages = listOf("Arabic", "English", "French"),
                location = "Egypt",
                gender = "Female",
                age = 25,
                joinedDate = "2022-01-01",
                targetingLevel = "B1"
            )
        )
    }
}
