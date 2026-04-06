package com.words.cards.domain

import com.words.cards.data.repository.UserLocalRepository
import com.words.cards.presentation.event.MainEvent

class GetSplashLoadedUseCase(
    private val userRepository: UserLocalRepository
) {
    suspend operator fun invoke(): MainEvent {
        return userRepository.getUser().fold(
            {
                MainEvent.GoToWordList
            },
            {
                MainEvent.GoToLogin
            }
        )
    }
}