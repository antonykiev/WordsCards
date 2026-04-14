package com.words.cards.presentation.state

import com.words.cards.domain.AppLanguages

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
        val appLanguage: AppLanguages,
    ) : Language {
        POLISH(AppLanguages.POLISH),
        ENGLISH(AppLanguages.ENGLISH),
        SPANISH(AppLanguages.SPANISH),
        GERMAN(AppLanguages.GERMAN),
        FRENCH(AppLanguages.FRENCH),
        RUSSIAN(AppLanguages.RUSSIAN),
        UKRAINIAN(AppLanguages.UKRAINIAN),
        SWEDISH(AppLanguages.SWEDISH),
        ITALIAN(AppLanguages.ITALIAN);

        val id: Long get() = appLanguage.id
        val flag: String get() = appLanguage.flag
        val languageName: String get() = appLanguage.languageName
    }
}

data class ButtonState(
    val text: String,
    val active: Boolean,
)