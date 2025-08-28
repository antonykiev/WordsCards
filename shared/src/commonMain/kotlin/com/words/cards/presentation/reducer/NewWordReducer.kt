package com.words.cards.presentation.reducer

import com.words.cards.data.db.entity.WordEntity
import com.words.cards.data.repository.WordRemoteRepository
import com.words.cards.domain.CurrentDateUseCase
import com.words.cards.domain.GetTranscriptionUseCase
import com.words.cards.domain.repository.WordLocalRepository
import com.words.cards.presentation.event.NewWordEvent
import com.words.cards.presentation.intent.NewWordIntent
import com.words.cards.presentation.state.State
import com.words.cards.presentation.state.NewWordScreenContent
import kotlinx.coroutines.flow.MutableStateFlow

class NewWordReducer(
    private val getTranscriptionUseCase: GetTranscriptionUseCase,
    private val wordRemoteRepository: WordRemoteRepository,
    private val wordLocalRepository: WordLocalRepository,
    private val currentDateUseCase: CurrentDateUseCase,
) : Reducer<NewWordEvent, NewWordScreenContent, NewWordIntent> {
    override val mutableState: MutableStateFlow<State<NewWordScreenContent, NewWordEvent>> =
        MutableStateFlow(
            State(
                isLoading = true,
                event = NewWordEvent.Empty,
                content = NewWordScreenContent.INITIAL
            )
        )

    override suspend fun processIntent(intent: NewWordIntent) {
        when (intent) {
            is NewWordIntent.InitialLoad -> {
                updateContent {
                    val transcription = getTranscriptionUseCase.invoke(intent.word)
                        .getOrElse { "Transcription not available" }
                    val wordInfo = wordRemoteRepository.getWordInfo(intent.word)
                        .onFailure {
                            println("NewWordReducer wordRemoteRepository.getWordInfo FAILED $it")
                        }
                        .getOrNull()

                    println("NewWordReducer wordInfo $wordInfo")

                    it.copy(
                        word = intent.word,
                        description = wordInfo?.description.orEmpty(),
                        transcription = transcription,
                        translation = wordInfo?.translation.orEmpty(),
                        exampleList = wordInfo?.sentences
                            .orEmpty()
                            .mapIndexed { index, item ->
                                "${index + 1}. $item"
                            }
                            .joinToString(separator = "\n"),

                    )
                }
            }

            NewWordIntent.OnSaveClicked -> {
                val wordInfo: NewWordScreenContent = mutableState.value.content
                wordLocalRepository.insertWord(
                    WordEntity(
                        wordText = wordInfo.word,
                        wordDescription = wordInfo.description,
                        wordTranslation = wordInfo.translation,
                        wordTranscription = wordInfo.transcription,
                        wordExamples = wordInfo.exampleList,
                        createdAt = currentDateUseCase.inSeconds()
                    )
                )
                updateEvent(NewWordEvent.Saved)
            }
        }
    }
}