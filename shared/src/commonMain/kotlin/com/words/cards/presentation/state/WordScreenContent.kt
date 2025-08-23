package com.words.cards.presentation.state

data class WordScreenContent(
    val word: String,
    val translation: LoadableContent,
    val transcription: LoadableContent,
    val exampleList: LoadableContent,
    val image: LoadableContent,
) {
    companion object {
        val INITIAL: WordScreenContent = WordScreenContent(
            word = "",
            translation = LoadableContent.Loading,
            transcription = LoadableContent.Loading,
            exampleList = LoadableContent.Loading,
            image = LoadableContent.Loading
        )
    }
}



