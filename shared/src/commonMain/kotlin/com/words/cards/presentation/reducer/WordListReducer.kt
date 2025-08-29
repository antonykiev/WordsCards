package com.words.cards.presentation.reducer

import com.words.cards.data.db.entity.WordEntity
import com.words.cards.domain.repository.WordLocalRepository
import com.words.cards.presentation.event.WordListEvent
import com.words.cards.presentation.intent.WordListIntent
import com.words.cards.presentation.state.State
import com.words.cards.presentation.state.WordItem
import com.words.cards.presentation.state.WordListScreenContent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.update

class WordListReducer(
    private val wordLocalRepository: WordLocalRepository,
    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.Default,
) : Reducer<WordListEvent, WordListScreenContent, WordListIntent> {

    override val mutableState: MutableStateFlow<State<WordListScreenContent, WordListEvent>> =
        MutableStateFlow(
            State(
                isLoading = true,
                event = WordListEvent.Empty,
                content = WordListScreenContent.INITIAL
            )
        )

    private val filterWord = MutableStateFlow("")

    override suspend fun processIntent(intent: WordListIntent) {
        when (intent) {
            WordListIntent.InitialLoad -> {
                combine(
                    wordLocalRepository.allWordsFlow(),
                    filterWord
                ) { words: List<WordEntity>, keyWord: String ->
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

                        if (keyWord.length > 2) {
                            it.copy(
                                searchWord = keyWord,
                                wordsItems = wordItems.filter { wordItem ->
                                    wordItem.word.contains(
                                        other = keyWord,
                                        ignoreCase = true
                                    ) || wordItem.translation.contains(
                                        other = keyWord,
                                        ignoreCase = true
                                    )
                                }
                            )
                        } else {
                            it.copy(
                                searchWord = keyWord,
                                wordsItems = wordItems
                            )
                        }
                    }
                }
                    .flowOn(coroutineDispatcher)
                    .collect()
            }

            is WordListIntent.OnPlusClicked -> {
                updateEvent(WordListEvent.OpenNewWord(intent.word.trim()))
                filterWord.update {
                    ""
                }
            }

            is WordListIntent.OnSearchWordChanged -> {
                filterWord.update {
                    intent.word.trim()
                }
            }

            is WordListIntent.OnWordClicked -> {
                updateEvent(WordListEvent.OpenWord(intent.wordId))
            }
        }
    }
}