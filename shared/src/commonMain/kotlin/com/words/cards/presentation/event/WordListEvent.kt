package com.words.cards.presentation.event

interface WordListEvent {
    data object Empty : WordListEvent

    data class OpenNewWord(
        val word: String,
    ) : WordListEvent

    data class OpenWord(
        val wordId: Long,
    ) : WordListEvent
}