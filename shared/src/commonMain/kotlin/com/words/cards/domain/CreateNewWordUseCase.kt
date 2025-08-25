package com.words.cards.domain

import com.words.cards.domain.entity.WordDomain
import com.words.cards.domain.repository.WordLocalRepository

class CreateNewWordUseCase(
    private val wordLocalRepository: WordLocalRepository
) {
    suspend operator fun invoke(wordDomain: WordDomain) {
        wordLocalRepository.insertWord(
            wordEntity = WordLocalRepository.mapToWordEntity(
                wordDomain = wordDomain
            )
        )
    }
}