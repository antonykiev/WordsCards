package com.words.cards.domain

class RemoveQuotesUseCase {
    operator fun invoke(word: String): String {
        return word.removeSurrounding("\"")
            .replace("\"", "")
    }
}