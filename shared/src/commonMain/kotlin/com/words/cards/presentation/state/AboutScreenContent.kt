package com.words.cards.presentation.state

data class AboutScreenContent(
    val title: String,
    val description: String,
) {
    companion object {
        val INITIAL = AboutScreenContent(
            title = "",
            description = "",
        )
    }
}
