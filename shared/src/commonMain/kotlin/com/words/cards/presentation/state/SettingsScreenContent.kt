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
) {
    companion object {

        val INITIAL = SettingsScreenContent(
            title = "Language Settings",
            subtitle = "Choose your learning languages",
            primaryDescription = "I speak",
            primary = Language.Selected(1, "🇵🇱", "Polish"),
            secondaryDescription = "I want to learn",
            secondary = Language.Empty,
            buttonState = ButtonState(
                text = "Continue",
                active = true
            ),
            languages = listOf(
                Language.Selected(1, "🇵🇱", "Polish"),
                Language.Selected(2, "🇺🇸", "English"),
                Language.Selected(3, "🇯🇵", "Japanese"),
                Language.Selected(4, "🇩🇪", "German"),
                Language.Selected(5, "🇫🇷", "French"),
                Language.Selected(6, "🇷🇺", "Russian"),
                Language.Selected(7, "🇺🇦", "Ukrainian"),
            )
        )
    }
}

sealed interface Language {
    data object Empty : Language
    data class Selected(
        val id: Long,
        val flag: String,
        val name: String,
    ) : Language
}

data class ButtonState(
    val text: String,
    val active: Boolean,
)