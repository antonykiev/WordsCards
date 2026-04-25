package com.words.cards.presentation.intent

sealed class AboutIntent {
    data object InitialLoad : AboutIntent()
    data object OnBackClicked : AboutIntent()
}
