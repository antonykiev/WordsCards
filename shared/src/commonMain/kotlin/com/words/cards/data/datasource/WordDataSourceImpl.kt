package com.words.cards.data.datasource

import com.words.cards.data.db.AppDatabase
import com.words.cards.data.db.entity.WordEntity
import kotlinx.coroutines.flow.Flow

class WordDataSourceImpl(
    private val database: AppDatabase
) : WordDataSource {

    override fun getAllWords(): Flow<List<WordEntity>> {
        return database.wordDao().getAllWords()
    }

    override suspend fun getWordById(id: Long): Result<WordEntity?> {
        return runCatching { database.wordDao().getWordById(id) }
    }

    override suspend fun insertWord(word: WordEntity): Result<Long> {
        return runCatching { database.wordDao().insertWord(word) }
    }

    override suspend fun updateWord(word: WordEntity): Result<Int> {
        return runCatching { database.wordDao().updateWord(word) }
    }

    override suspend fun deleteWord(word: WordEntity): Result<Int> {
        return runCatching { database.wordDao().deleteWord(word) }
    }
}