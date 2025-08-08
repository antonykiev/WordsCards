package com.words.cards.presentation.state

import com.words.cards.resource.Image

data class MainScreenContent(
    val items: List<Item>,
) {
    companion object {
        val INITIAL = MainScreenContent(
            items = emptyList()
        )
    }

    data class Item(
        val image: Image,
        val text: String,
        val route: String
    )
}

