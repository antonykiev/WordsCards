package com.words.cards.presentation.intent

sealed interface MainIntent {
    data object SplashLoaded : MainIntent
    data object ContinueAsGuest : MainIntent
    data object ContinueAsUser : MainIntent
}