package com.words.cards.presentation.state

data class NewWordScreenContent(
    val word: String,
    val description: String,
    val translation: String,
    val transcription: String,
    val exampleList: String,
    val image: LoadableContent,
) {
    companion object {
        val INITIAL: NewWordScreenContent = NewWordScreenContent(
            word = "",
            description = "",
            translation = "",
            transcription = "",
            exampleList = "",
            image = LoadableContent.Loading
        )
    }
}





