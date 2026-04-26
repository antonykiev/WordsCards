package com.words.cards.presentation.state

data class WordListScreenContent(
    val searchWord: String,
    val wordsItems: List<WordItem>,
) {
    companion object {
        val INITIAL = WordListScreenContent(
            searchWord = "",
            wordsItems = emptyList()
        )
    }
}

data class WordItem(
    val id: Long,
    val word: String,    val transcription: String,
    val showTranscription: Boolean,
    val translation: String,
    val showTranslation: Boolean,
    val description: String,
    val showDescription: Boolean,
)
