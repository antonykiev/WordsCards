package com.words.cards.data.datasource

import com.words.cards.data.db.entity.UserEntity

interface UserDataSource {
    suspend fun saveUser(user: UserEntity)
    suspend fun getUser(): Result<UserEntity>
    suspend fun updateUser(user: UserEntity)
    suspend fun deleteUser()
}