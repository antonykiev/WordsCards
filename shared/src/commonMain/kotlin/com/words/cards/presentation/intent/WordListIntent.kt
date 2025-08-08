package com.words.cards.presentation.intent

sealed class WordListIntent {

    data object InitialLoad : WordListIntent()

    data class OnWordClicked(
        val wordId: Long,
    ) : WordListIntent()

    data class OnPlusClicked(
        val word: String,
    ) : WordListIntent()

    data class OnSearchWordChanged(
        val word: String,
    ) : WordListIntent()
}