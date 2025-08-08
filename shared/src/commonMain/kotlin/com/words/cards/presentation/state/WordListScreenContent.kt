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
    val word: String,
    val translation: String,
    val description: String
)