package com.words.cards.data.repository

import com.words.cards.data.datasource.UserDataSource
import com.words.cards.data.db.entity.UserEntity

class UserLocalRepositoryImpl(
    private val dataSource: UserDataSource
) : UserLocalRepository {

    override suspend fun saveUser(user: UserEntity) {
        dataSource.saveUser(user)
    }

    override suspend fun getUser(): Result<UserEntity> {
        return dataSource.getUser()
    }

    override suspend fun updateUser(user: UserEntity) {
        dataSource.updateUser(user)
    }
    override suspend fun deleteUser() {
        dataSource.deleteUser()
    }
}