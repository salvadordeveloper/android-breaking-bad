package com.salvador.breakingbad.data.repository

import com.salvador.breakingbad.data.remote.BreakingBadApi
import com.salvador.breakingbad.data.remote.dto.CharacterDto
import com.salvador.breakingbad.domain.repository.CharacterRespository
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val api : BreakingBadApi
) : CharacterRespository {

    override suspend fun getCharacters(): List<CharacterDto> {
        return api.getCharacters();
    }

    override suspend fun getCharacterById(characterId: String): CharacterDto {
        return api.getCharacterById(characterId).first()
    }

}