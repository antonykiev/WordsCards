package com.words.cards.presentation.state

data class SettingsScreenContent(
    val title: String,
    val subtitle: String,
    val primaryDescription: String,
    val primary: Language,
    val secondaryDescription: String,
    val secondary: Language,
    val buttonState: ButtonState,
    val languages: List<Language.Selected>,
)

sealed interface Language {
    data object Empty: Language
    data class Selected(
        val id: Long,
        val flag: String,
        val name: String,
    ): Language
}

data class ButtonState(
    val text: String,
    val active: Boolean,
)