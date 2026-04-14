package com.words.cards.domain

enum class AppLanguages(
    val id: Long,
    val flag: String,
    val languageName: String,
) {
    POLISH(1, "🇵🇱", "Polish"),
    ENGLISH(2, "🇺🇸", "English"),
    SPANISH(3, "🇪🇸", "Spanish"),
    GERMAN(4, "🇩🇪", "German"),
    FRENCH(5, "🇫🇷", "French"),
    RUSSIAN(6, "🇷🇺", "Russian"),
    UKRAINIAN(7, "🇺🇦", "Ukrainian"),
    SWEDISH(8, "🇸🇪", "Swedish"),
    ITALIAN(9, "🇮🇹", "ITALIAN");
}