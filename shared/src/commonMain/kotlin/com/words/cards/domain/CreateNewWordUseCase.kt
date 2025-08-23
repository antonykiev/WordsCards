package com.words.cards.domain

import com.words.cards.domain.entity.WordDomain
import com.words.cards.domain.repository.WordRepository

class CreateNewWordUseCase(
    private val wordRepository: WordRepository
) {
    suspend operator fun invoke(wordDomain: WordDomain) {
        wordRepository.insertWord(
            wordEntity = WordRepository.mapToWordEntity(
                wordDomain = wordDomain
            )
        )
    }
}