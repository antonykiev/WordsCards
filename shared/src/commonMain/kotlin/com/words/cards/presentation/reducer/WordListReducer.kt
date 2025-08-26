package com.words.cards.presentation.reducer

import com.words.cards.data.db.entity.WordEntity
import com.words.cards.domain.repository.WordLocalRepository
import com.words.cards.presentation.event.WordListEvent
import com.words.cards.presentation.intent.WordListIntent
import com.words.cards.presentation.state.State
import com.words.cards.presentation.state.WordItem
import com.words.cards.presentation.state.WordListScreenContent
import kotlinx.coroutines.flow.MutableStateFlow

class WordListReducer(
    private val wordLocalRepository: WordLocalRepository,
) : Reducer<WordListEvent, WordListScreenContent, WordListIntent> {
    override val mutableState: MutableStateFlow<State<WordListScreenContent, WordListEvent>> =
        MutableStateFlow(
            State(
                isLoading = true,
                event = WordListEvent.Empty,
                content = WordListScreenContent.INITIAL
            )
        )

    override suspend fun processIntent(intent: WordListIntent) {
        when (intent) {
            WordListIntent.InitialLoad -> {
                wordLocalRepository.allWordsFlow()
                    .collect { words: List<WordEntity> ->
                        updateContent {
                            val wordItems = words.map { word: WordEntity ->
                                WordItem(
                                    id = word.id,
                                    word = word.wordText,
                                    transcription = word.wordTranscription,
                                    translation = word.wordTranslation,
                                    description = word.wordDescription
                                )
                            }
                            it.copy(wordsItems = wordItems)
                        }
                    }
            }

            is WordListIntent.OnPlusClicked -> {
                updateEvent(WordListEvent.OpenNewWord(intent.word.trim()))
            }

            is WordListIntent.OnSearchWordChanged -> {
                updateContent {
                    it.copy(searchWord = intent.word)
                }
            }

            is WordListIntent.OnWordClicked -> {
                updateEvent(WordListEvent.OpenWord(intent.wordId))
            }
        }
    }
}