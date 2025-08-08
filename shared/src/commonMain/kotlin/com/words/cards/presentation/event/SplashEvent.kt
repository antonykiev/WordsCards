package com.words.cards.presentation.event

sealed interface SplashEvent {
    data object Empty : SplashEvent
    data object LoadedSuccessfully : SplashEvent
    data object LoadedWithError : SplashEvent
}