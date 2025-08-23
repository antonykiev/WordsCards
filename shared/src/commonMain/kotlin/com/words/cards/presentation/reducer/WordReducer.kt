package com.words.cards.presentation.reducer

import com.words.cards.domain.GetTranscriptionUseCase
import com.words.cards.presentation.event.WordEvent
import com.words.cards.presentation.intent.WordIntent
import com.words.cards.presentation.state.State
import com.words.cards.presentation.state.TranscriptionState
import com.words.cards.presentation.state.WordScreenContent
import kotlinx.coroutines.flow.MutableStateFlow

class WordReducer(
    private val getTranscriptionUseCase: GetTranscriptionUseCase
) : Reducer<WordEvent, WordScreenContent, WordIntent> {
    override val mutableState: MutableStateFlow<State<WordScreenContent, WordEvent>> =
        MutableStateFlow(
            State(
                isLoading = true,
                event = WordEvent.Empty,
                content = WordScreenContent.INITIAL
            )
        )

    override suspend fun processIntent(intent: WordIntent) {
        when (intent) {
            is WordIntent.InitialLoad -> {
                updateContent {
                    val transcription = getTranscriptionUseCase.invoke(intent.word)
                        .getOrElse { "Transcription not available" }

                    it.copy(
                        word = intent.word,
                        transcription = TranscriptionState.Loaded(data = transcription)
                    )
                }
            }
        }
    }
}