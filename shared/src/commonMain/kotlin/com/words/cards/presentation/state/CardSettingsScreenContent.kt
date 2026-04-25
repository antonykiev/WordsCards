package com.words.cards.presentation.state

data class CardSettingsScreenContent(
    val title: String,
    val showTranscriptionLabel: String,
    val isShowTranscriptionEnabled: Boolean,
    val showDescriptionLabel: String,
    val isShowDescriptionEnabled: Boolean,
    val showTranslationLabel: String,
    val isShowTranslationEnabled: Boolean,
    val showExampleLabel: String,
    val isShowExampleEnabled: Boolean,
) {
    companion object {
        val INITIAL = CardSettingsScreenContent(
            title = "",
            showTranscriptionLabel = "",
            isShowTranscriptionEnabled = true,
            showDescriptionLabel = "",
            isShowDescriptionEnabled = true,
            showTranslationLabel = "",
            isShowTranslationEnabled = true,
            showExampleLabel = "",
            isShowExampleEnabled = true,
        )
    }
}
