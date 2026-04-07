package com.words.cards.presentation.state

data class SettingsScreenContent(
    val vocabularies: List<Vocabulary>,
    val languages: List<Language.Selected>,
    val buttonState: ButtonState
)

sealed interface Language {
    data object Empty: Language
    data class Selected(
        val id: Long,
        val flag: String,
        val name: String,
    ): Language
}

data class Vocabulary(
    val id: Long,
    val primary: Language,
    val secondary: Language,
)

data class ButtonState(
    val text: String,
    val active: Boolean,
)