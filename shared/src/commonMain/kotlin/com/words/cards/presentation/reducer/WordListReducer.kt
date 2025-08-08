package com.words.cards.presentation.reducer

import com.words.cards.presentation.event.WordListEvent
import com.words.cards.presentation.intent.WordListIntent
import com.words.cards.presentation.state.State
import com.words.cards.presentation.state.WordListScreenContent
import kotlinx.coroutines.flow.MutableStateFlow

class WordListReducer : Reducer<WordListEvent, WordListScreenContent, WordListIntent> {
    override val mutableState: MutableStateFlow<State<WordListScreenContent, WordListEvent>> =
        MutableStateFlow(
            State(
                isLoading = true,
                event = WordListEvent.Empty,
                content = WordListScreenContent.INITIAL
            )
        )

    override suspend fun processIntent(intent: WordListIntent) {
        when(intent) {
            WordListIntent.InitialLoad -> {

            }
            is WordListIntent.OnPlusClicked -> TODO()
            is WordListIntent.OnSearchWordChanged -> {
                updateContent {
                    it.copy(searchWord = intent.word)
                }
            }
            is WordListIntent.OnWordClicked -> TODO()
        }
    }
}