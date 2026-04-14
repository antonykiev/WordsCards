package com.words.cards.presentation.intent

import com.words.cards.presentation.state.Language

sealed class SettingsIntent {
    data object InitialLoad: SettingsIntent()
    data class SelectLanguage(
        val isPrimary: Boolean,
        val language: Language.Selected
    ) : SettingsIntent()
    data object OnNextClicked : SettingsIntent()
}