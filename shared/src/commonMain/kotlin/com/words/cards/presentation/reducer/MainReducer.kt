package com.words.cards.presentation.reducer

import com.words.cards.presentation.event.MainEvent
import com.words.cards.presentation.intent.MainIntent
import com.words.cards.presentation.state.MainScreenContent
import com.words.cards.presentation.state.State
import com.words.cards.resource.ResourceIds
import com.words.cards.resource.ResourceProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class MainReducer(
    private val resourceProvider: ResourceProvider,
) : Reducer<MainEvent, MainScreenContent, MainIntent> {
    override val mutableState: MutableStateFlow<State<MainScreenContent, MainEvent>> =
        MutableStateFlow(
            State(
                isLoading = true,
                event = MainEvent.Empty,
                content = MainScreenContent.INITIAL
            )
        )

    override suspend fun processIntent(intent: MainIntent) {
        when (intent) {
            MainIntent.InitialLoad -> {
                updateContent {
                    MainScreenContent(
                        items = listOf(
                            MainScreenContent.Item(
                                text = "Words",
                                image = resourceProvider.getDrawable(ResourceIds.Drawables.ICON_WORD_LIST),
                                route = "word_list"
                            ),
                            MainScreenContent.Item(
                                text = "Settings",
                                image = resourceProvider.getDrawable(ResourceIds.Drawables.ICON_SETTINGS),
                                route = "settings"
                            )
                        )
                    )
                }
            }

            MainIntent.OnSettingsClicked -> {

            }

            MainIntent.OnWordListClicked -> {

            }

            is MainIntent.OpenNewWord -> {
                updateEvent(MainEvent.OpenWord(intent.newWord))
            }
        }
    }
}