package com.salvador.breakingbad.domain.repository

import com.salvador.breakingbad.data.local.dto.FavoriteCharacter
import kotlinx.coroutines.flow.Flow

interface FavoriteCharacterRepository {

    fun getFavorites() : Flow<List<FavoriteCharacter>>

    suspend fun getFavoriteByID(id: Int) : FavoriteCharacter?

    suspend fun insertFavorite(favoriteCharacter : FavoriteCharacter)

    suspend fun  deleteFavorite(favoriteCharacter: FavoriteCharacter)
}