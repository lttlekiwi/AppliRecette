package com.example.myapplication

data class Recettedb(
    var steps: MutableList<String> = mutableListOf(),
    var ingredients: MutableList<String> = mutableListOf(),
    var title: String = "",
    var grade: String = "",
    var idediteur: String = "",
    var end: String = "",
    var numberOfPeople: String = "",
    var timeToCook: String = "",
    var timeToSpend: String = ""
)
