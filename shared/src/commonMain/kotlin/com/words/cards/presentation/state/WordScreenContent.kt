package com.words.cards.presentation.state

data class WordScreenContent(
    val word: String,
    val description: String,
    val translation: String,
    val transcription: String,
    val exampleList: String,
    val image: LoadableContent,
) {
    companion object {
        val INITIAL: WordScreenContent = WordScreenContent(
            word = "",
            description = "",
            translation = "",
            transcription = "",
            exampleList = "",
            image = LoadableContent.Loading
        )
    }
}





