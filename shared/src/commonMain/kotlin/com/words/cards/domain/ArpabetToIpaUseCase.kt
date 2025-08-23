package com.words.cards.domain

class ArpabetToIpaUseCase {

    private val arpabetToIpa = mapOf(
        // Consonants
        "P" to "p", "B" to "b", "T" to "t", "D" to "d", "K" to "k", "G" to "ɡ",
        "F" to "f", "V" to "v", "TH" to "θ", "DH" to "ð", "S" to "s", "Z" to "z",
        "SH" to "ʃ", "ZH" to "ʒ", "CH" to "tʃ", "JH" to "dʒ", "M" to "m", "N" to "n",
        "NG" to "ŋ", "L" to "l", "R" to "ɹ", "Y" to "j", "W" to "w", "HH" to "h",
        // Vowels (base forms without stress)
        "AA" to "ɑ", "AE" to "æ", "AH" to "ʌ", "AO" to "ɔ", "AW" to "aʊ",
        "AY" to "aɪ", "EH" to "ɛ", "ER" to "ə", "EY" to "eɪ", "IH" to "ɪ",
        "IY" to "i", "OW" to "oʊ", "OY" to "ɔɪ", "UH" to "ʊ", "UW" to "u"
    )


    operator fun invoke(arpabet: String): String {
        val phonemes: List<String> = arpabet.trim().split(" ")

        val ipaPhonemes = mutableListOf<String>()

        for (phoneme in phonemes) {
            val stress = phoneme.takeLast(1)
            val basePhoneme = phoneme.dropLast(if (phoneme.length > 1 && stress.toIntOrNull() != null) 1 else 0)

            val ipaBase = arpabetToIpa[basePhoneme] ?: continue

            val ipa = when (stress) {
                "1" -> "ˈ$ipaBase"
                "2" -> "ˌ$ipaBase"
                else -> ipaBase
            }
            ipaPhonemes.add(ipa)
        }
        return "[${ipaPhonemes.joinToString("")}]"
    }
}