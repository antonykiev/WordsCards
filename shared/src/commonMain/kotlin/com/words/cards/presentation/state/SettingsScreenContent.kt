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
            primary = Language.Selected.POLISH,
            secondaryDescription = "I want to learn",
            secondary = Language.Empty,
            buttonState = ButtonState(
                text = "Continue",
                active = true
            ),
            languages = Language.Selected.entries
        )
    }
}

sealed interface Language {
    data object Empty : Language
    enum class Selected(
        val id: Long,
        val flag: String,
        val languageName: String,
    ) : Language {
        POLISH(1, "🇵🇱", "Polish"),
        ENGLISH(2, "🇺🇸", "English"),
        JAPANESE(3, "🇯🇵", "Japanese"),
        GERMAN(4, "🇩🇪", "German"),
        FRENCH(5, "🇫🇷", "French"),
        RUSSIAN(6, "🇷🇺", "Russian"),
        UKRAINIAN(7, "🇺🇦", "Ukrainian");
    }
}

data class ButtonState(
    val text: String,
    val active: Boolean,
)