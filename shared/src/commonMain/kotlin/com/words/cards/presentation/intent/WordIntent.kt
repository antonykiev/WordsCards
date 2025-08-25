package com.words.cards.presentation.intent

sealed class WordIntent {
    data object OnSaveClicked : WordIntent()

    data class InitialLoad(
        val word: String
    ) : WordIntent()
}