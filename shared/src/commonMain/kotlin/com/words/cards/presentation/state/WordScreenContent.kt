package com.words.cards.presentation.state

data class WordScreenContent(
    val word: String,
    val translation: LoadableContent,
    val transcription: TranscriptionState,
    val exampleList: LoadableContent,
    val image: LoadableContent,
) {
    companion object {
        val INITIAL: WordScreenContent = WordScreenContent(
            word = "",
            translation = LoadableContent.Loading,
            transcription = TranscriptionState.Loading,
            exampleList = LoadableContent.Loading,
            image = LoadableContent.Loading
        )
    }
}

sealed interface TranscriptionState {
    data object Loading : TranscriptionState

    data class Loaded(
        val data: String
    ) : TranscriptionState

    data class Error(
        val error: Throwable
    ) : TranscriptionState
}





