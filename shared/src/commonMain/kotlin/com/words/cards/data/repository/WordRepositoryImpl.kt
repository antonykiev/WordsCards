package com.words.cards.data.repository

import com.words.cards.data.datasource.WordDataSource
import com.words.cards.data.db.entity.WordEntity
import com.words.cards.domain.repository.WordRepository
import kotlinx.coroutines.flow.Flow

class WordRepositoryImpl(
    private val dataSource: WordDataSource
) : WordRepository {

    override fun getAllWords(): Flow<List<WordEntity>> {
        return dataSource.getAllWords()
    }

    override suspend fun getWordById(id: Long): Result<WordEntity?> {
        return dataSource.getWordById(id)
    }

    override suspend fun insertWord(wordEntity: WordEntity): Result<Long> {
        return dataSource.insertWord(wordEntity)
    }

    override suspend fun updateWord(word: WordEntity): Result<Int> {
        return dataSource.updateWord(word)
    }

    override suspend fun deleteWord(word: WordEntity): Result<Int> {
        return dataSource.deleteWord(word)
    }
}