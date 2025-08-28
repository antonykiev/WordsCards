package com.words.cards.presentation.reducer

import com.words.cards.data.db.entity.WordEntity
import com.words.cards.domain.repository.WordLocalRepository
import com.words.cards.presentation.event.WordEvent
import com.words.cards.presentation.intent.WordIntent
import com.words.cards.presentation.state.State
import com.words.cards.presentation.state.WordScreenContent
import kotlinx.coroutines.flow.MutableStateFlow

class WordReducer(
    private val wordLocalRepository: WordLocalRepository,
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
                wordLocalRepository.getWordById(intent.wordId).fold(
                    onSuccess = { word: WordEntity ->
                        println("WordReducer - InitialLoad - onSuccess $intent")
                        updateContent {
                            it.copy(
                                word = word.wordText,
                                description = word.wordDescription,
                                translation = word.wordTranslation,
                                transcription = word.wordTranscription,
                                exampleList = word.wordExamples,
                            )
                        }
                    },
                    onFailure = {
                        println("WordReducer - InitialLoad - onFailure $intent ${it.message}")
                    }
                )
            }
        }
    }
}