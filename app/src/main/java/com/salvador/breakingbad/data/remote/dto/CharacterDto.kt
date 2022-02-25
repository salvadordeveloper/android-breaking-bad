package com.salvador.breakingbad.data.remote.dto

import com.salvador.breakingbad.domain.model.Character

data class CharacterDto(
    val appearance: List<Int>,
    val better_call_saul_appearance: List<Any>,
    val birthday: String,
    val category: String,
    val char_id: Int,
    val img: String,
    val name: String,
    val nickname: String,
    val occupation: List<String>,
    val portrayed: String,
    val status: String
)

fun CharacterDto.toCharacter() : Character {
    return Character(
        appearance = appearance,
        char_id = char_id,
        img = img,
        name = name,
        nickname = nickname,
        occupation =  occupation,
        portrayed = portrayed,
        status = status,
        isFavorite = false
    )
}
