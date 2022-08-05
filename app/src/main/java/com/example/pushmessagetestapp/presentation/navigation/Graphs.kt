package com.example.pushmessagetestapp.presentation.navigation

sealed class Graphs(val graphName: String) {

    object LoginGraph : Graphs("loginGraph")

    object MainGraph : Graphs("mainGraph")
}
