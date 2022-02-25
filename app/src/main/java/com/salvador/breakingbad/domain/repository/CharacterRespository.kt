package com.salvador.breakingbad.domain.repository

import com.salvador.breakingbad.data.remote.dto.CharacterDto

interface CharacterRespository{

    suspend fun getCharacters() : List<CharacterDto>

    suspend fun getCharacterById(characterId : String) : CharacterDto
}