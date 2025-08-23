package com.words.cards.presentation.reducer

import com.words.cards.presentation.event.WordEvent
import com.words.cards.presentation.intent.WordIntent
import com.words.cards.presentation.state.State
import com.words.cards.presentation.state.WordScreenContent
import kotlinx.coroutines.flow.MutableStateFlow

class WordReducer(

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
            WordIntent.InitialLoad -> TODO()
        }
    }
}