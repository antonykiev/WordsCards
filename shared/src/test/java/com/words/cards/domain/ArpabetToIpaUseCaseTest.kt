package com.words.cards.domain

import kotlin.test.Test
import kotlin.test.assertEquals

class ArpabetToIpaUseCaseTest {

    private val useCase = ArpabetToIpaUseCase()

    @Test
    fun `converts single phoneme without stress correctly`() {
        // Arrange
        val arpabet = "T"

        // Act
        val result = useCase.invoke(arpabet)

        // Assert
        assertEquals("[t]", result)
    }

    @Test
    fun `converts single phoneme with primary stress correctly`() {
        // Arrange
        val arpabet = "T1"

        // Act
        val result = useCase.invoke(arpabet)

        // Assert
        assertEquals("[ˈt]", result)
    }

    @Test
    fun `converts single phoneme with secondary stress correctly`() {
        // Arrange
        val arpabet = "T2"

        // Act
        val result = useCase.invoke(arpabet)

        // Assert
        assertEquals("[ˌt]", result)
    }

    @Test
    fun `converts multiple phonemes with mixed stress correctly`() {
        // Arrange
        val arpabet = "T EH1 S T"

        // Act
        val result = useCase.invoke(arpabet)

        // Assert
        assertEquals("[tˈɛst]", result)
    }

    @Test
    fun `handles invalid phoneme by skipping it`() {
        // Arrange
        val arpabet = "T XYZ EH1"

        // Act
        val result = useCase.invoke(arpabet)

        // Assert
        assertEquals("[tˈɛ]", result)
    }

    @Test
    fun `returns empty brackets for empty input`() {
        // Arrange
        val arpabet = ""

        // Act
        val result = useCase.invoke(arpabet)

        // Assert
        assertEquals("[]", result)
    }

    @Test
    fun `handles input with only invalid phonemes`() {
        // Arrange
        val arpabet = "XYZ ABC"

        // Act
        val result = useCase.invoke(arpabet)

        // Assert
        assertEquals("[]", result)
    }

    @Test
    fun `handles phonemes with spaces and mixed stress`() {
        // Arrange
        val arpabet = "S AY1 L AH0 N S"

        // Act
        val result = useCase.invoke(arpabet)

        // Assert
        assertEquals("[sˈaɪlʌns]", result)
    }

    @Test
    fun `handles phoneme example`() {
        // Arrange
        val arpabet = "IH0 G Z AE1 M P AH0 L"

        // Act
        val result = useCase.invoke(arpabet)

        // Assert
        assertEquals("[ɪɡzˈæmpʌl]", result)
    }
}