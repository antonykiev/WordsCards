package com.words.cards.presentation.intent

sealed class MainIntent {
    data object InitialLoad : MainIntent()
    data object OnWordListClicked : MainIntent()
    data object OnSettingsClicked : MainIntent()

    data class OpenNewWord(
        val newWord: String
    ) : MainIntent()
}