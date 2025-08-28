package com.words.cards.presentation.intent

sealed class NewWordIntent {
    data object OnSaveClicked : NewWordIntent()

    data class InitialLoad(
        val word: String
    ) : NewWordIntent()
}