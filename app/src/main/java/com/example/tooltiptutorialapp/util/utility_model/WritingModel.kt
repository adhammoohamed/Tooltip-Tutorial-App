package com.example.tooltiptutorialapp.util.utility_model

data class WritingModel(
    val title: String,
    val answeredQuestion: Int,
    val progress: Int,
)

val writingModels = mutableListOf<WritingModel>()

fun populateWritingModels() {
    (1..12).forEach {
        if (it % 2 == 0) {

            if (it < 3) {
                writingModels.add(

                    WritingModel(
                        title = "Technologie",
                        answeredQuestion = 10,
                        progress = 100
                    )
                )
            } else {
                writingModels.add(

                    WritingModel(
                        title = if (it != 8) "Voiage" else "immigrations",
                        answeredQuestion = 5,
                        progress = 50
                    )
                )
            }
        } else {
            if (it < 3) {
                writingModels.add(

                    WritingModel(
                        title = "Art er Culture",
                        answeredQuestion = 5,
                        progress = 50
                    )
                )
            } else {
                writingModels.add(

                    WritingModel(
                        title = if (it != 8) "Enviroment" else "Travel",
                        answeredQuestion = 10,
                        progress = 100
                    )
                )
            }
        }

    }
}