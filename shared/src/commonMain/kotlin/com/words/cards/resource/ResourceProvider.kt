package com.words.cards.resource

expect class ResourceProvider {
    fun getString(id: String): String
    fun getDrawable(id: String): Image
}

