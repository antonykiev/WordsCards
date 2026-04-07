package com.words.cards.presentation.intent

sealed class SettingsIntent {
    data object InitialLoad: SettingsIntent()
}