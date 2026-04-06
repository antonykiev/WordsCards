package com.words.cards.data.datasource

import com.words.cards.data.db.AppDatabase
import com.words.cards.data.db.entity.UserEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

class UserDataSourceImpl(
    private val database: AppDatabase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : UserDataSource {
    override suspend fun saveUser(user: UserEntity) {
        withContext(dispatcher) {
            database.userDao().saveUser(user)
        }
    }

    override suspend fun getUser(): Result<UserEntity> {
        return runCatching {
            withContext(dispatcher) {
                database.userDao().getUser()!!
            }
        }
    }

    override suspend fun updateUser(user: UserEntity) {
        withContext(dispatcher) {
            database.userDao().updateUser(user)
        }
    }

    override suspend fun deleteUser() {
        withContext(dispatcher) {
            database.userDao().deleteUser()
        }
    }
}
