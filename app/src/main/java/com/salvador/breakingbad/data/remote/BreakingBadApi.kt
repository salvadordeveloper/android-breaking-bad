package com.salvador.breakingbad.data.remote

import com.salvador.breakingbad.data.remote.dto.CharacterDto
import retrofit2.http.GET
import retrofit2.http.Path

interface BreakingBadApi{

    @GET("/api/characters")
    suspend fun getCharacters(): List<CharacterDto>

    @GET("/api/characters/{characterId}")
    suspend fun getCharacterById(@Path("characterId") characterId : String) : List<CharacterDto>
}