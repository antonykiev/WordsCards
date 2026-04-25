package com.words.cards.presentation.intent

sealed class CardSettingsIntent {
    data object InitialLoad : CardSettingsIntent()
    data object OnBackClicked : CardSettingsIntent()
    data class OnShowTranscriptionToggled(val show: Boolean) : CardSettingsIntent()
    data class OnShowDescriptionToggled(val show: Boolean) : CardSettingsIntent()
    data class OnShowTranslationToggled(val show: Boolean) : CardSettingsIntent()
    data class OnShowExampleToggled(val show: Boolean) : CardSettingsIntent()
}
