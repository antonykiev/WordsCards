package com.words.cards.presentation.intent

sealed class WordIntent {

    data class InitialLoad(
        val word: String
    ) : WordIntent()

}