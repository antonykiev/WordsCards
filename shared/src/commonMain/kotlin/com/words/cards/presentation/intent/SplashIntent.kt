package com.words.cards.presentation.intent

sealed class SplashIntent {
    data object InitialLoad: SplashIntent()
}