package com.example.tooltiptutorialapp.util.utility_model

data class UnitItem(
    val unitNumber: String,
    val unitTitle: String,
)

val unitList = mutableListOf<UnitItem>()



fun populateUnitList() {
    unitList.clear() // Clear the list to avoid duplicate items on recomposition
    (1..10).forEach {
        if (it % 2 != 0) {
            unitList.add(
                UnitItem(
                    unitNumber = it.toString(),
                    unitTitle = "What is examate",
                )
            )
        } else {
            unitList.add(
                UnitItem(
                    unitNumber = it.toString(),
                    unitTitle = "What is TCF",
                )
            )
        }
    }
}