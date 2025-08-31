package com.words.cards.presentation.state

data class WordScreenContent(
    val wordId: Long,
    val word: String,
    val description: String,
    val translation: String,
    val transcription: String,
    val exampleList: String,
) {
    companion object {
        val INITIAL: WordScreenContent = WordScreenContent(
            wordId = -1L,
            word = "",
            description = "",
            translation = "",
            transcription = "",
            exampleList = "",
        )
    }
}





