package com.salvador.breakingbad.domain.model

data class Character(
    val appearance: List<Int>,
    val char_id: Int,
    val img: String,
    val name: String,
    val nickname: String,
    val occupation: List<String>,
    val portrayed: String,
    val status: String,
    val isFavorite : Boolean
)