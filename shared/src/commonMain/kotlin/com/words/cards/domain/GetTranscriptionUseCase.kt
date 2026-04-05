package com.words.cards.domain

class GetTranscriptionUseCase(
    private val getFileJsonUseCase: GetFileJsonUseCase,
    private val arpabetToIpaUseCase: ArpabetToIpaUseCase,
    private val removeQuotesUseCase: RemoveQuotesUseCase,
) {
    suspend operator fun invoke(word: String): Result<String> {
        return getFileJsonUseCase.loadJsonFromAssets("cmudict.json")
            .mapCatching {
                it[word] ?: throw IllegalArgumentException("Word not found in dictionary")
            }
            .map { "$it" }
            .map {
                val stringWithoutQuotes = removeQuotesUseCase.invoke(it)
                arpabetToIpaUseCase.invoke(stringWithoutQuotes)
            }
    }
}