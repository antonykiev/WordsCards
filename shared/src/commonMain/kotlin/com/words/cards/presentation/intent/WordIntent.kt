package com.words.cards.presentation.intent

sealed class WordIntent {

    data object InitialLoad : WordIntent()

}