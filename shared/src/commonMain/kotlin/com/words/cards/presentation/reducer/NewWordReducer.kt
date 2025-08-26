package com.words.cards.presentation.reducer

import com.words.cards.data.db.entity.WordEntity
import com.words.cards.data.repository.WordLocalRepositoryImpl
import com.words.cards.data.repository.WordRemoteRepository
import com.words.cards.domain.GetTranscriptionUseCase
import com.words.cards.domain.repository.WordLocalRepository
import com.words.cards.presentation.event.WordEvent
import com.words.cards.presentation.intent.WordIntent
import com.words.cards.presentation.state.State
import com.words.cards.presentation.state.WordScreenContent
import kotlinx.coroutines.flow.MutableStateFlow

class NewWordReducer(
    private val getTranscriptionUseCase: GetTranscriptionUseCase,
    private val wordRemoteRepository: WordRemoteRepository,
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

            WordIntent.OnSaveClicked -> {
                val wordInfo: WordScreenContent = mutableState.value.content
                wordLocalRepository.insertWord(
                    WordEntity(
                        wordText = wordInfo.word,
                        wordDescription = wordInfo.description,
                        wordTranslation = wordInfo.translation,
                        wordTranscription = wordInfo.transcription,
                        createdAt = 1L
                    )
                )
            }
        }
    }
}