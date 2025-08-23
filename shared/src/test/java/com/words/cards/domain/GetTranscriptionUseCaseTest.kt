package com.words.cards.domain

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class GetTranscriptionUseCaseTest {

        private val getFileJsonUseCase: GetFileJsonUseCase = mockk()
        private val arpabetToIpaUseCase: ArpabetToIpaUseCase = ArpabetToIpaUseCase()
        private val useCase = GetTranscriptionUseCase(getFileJsonUseCase, arpabetToIpaUseCase)

    @Test
    fun `invoke returns IPA transcription when word exists in dictionary`() = runTest {
        // Arrange
        val word = "test"
        val arpabet = "T EH1 S T"
        val ipa = "[tˈɛst]"
        val jsonObject = buildJsonObject { put(word, arpabet) }

        coEvery { getFileJsonUseCase.loadJsonFromAssets("cmudict.json") } returns Result.success(jsonObject)

        // Act
        val result = useCase.invoke(word)

        // Assert
        assertTrue(result.isSuccess)
        assertEquals(ipa, result.getOrNull())
    }

    @Test
    fun `invoke returns failure when word is not found in dictionary`() = runTest {
        // Arrange
        val word = "unknown"
        val jsonObject = buildJsonObject { /* empty */ }

        coEvery { getFileJsonUseCase.loadJsonFromAssets("cmudict.json") } returns Result.success(jsonObject)

        // Act
        val result = useCase.invoke(word)

        // Assert
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is IllegalArgumentException)
        assertEquals("Word not found in dictionary", result.exceptionOrNull()?.message)
    }

    @Test
    fun `invoke returns failure when JSON loading fails`() = runTest {
        // Arrange
        val word = "test"
        val errorMessage = "File not found"
        val jsonObject = buildJsonObject { put("error", errorMessage) }

        coEvery { getFileJsonUseCase.loadJsonFromAssets("cmudict.json") } returns Result.success(jsonObject)

        // Act
        val result = useCase.invoke(word)

        // Assert
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is IllegalArgumentException)
        assertEquals("Word not found in dictionary", result.exceptionOrNull()?.message)
    }
}