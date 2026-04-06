package com.words.cards.domain

import com.words.cards.data.db.entity.UserEntity
import com.words.cards.data.repository.UserLocalRepository

class CreateGuestUserUseCase(
    private val userRepository: UserLocalRepository
) {
    suspend operator fun invoke() {
        userRepository.saveUser(
            UserEntity(
                email = "",
                password = "",
                isGuest = true
            )
        )
    }
}