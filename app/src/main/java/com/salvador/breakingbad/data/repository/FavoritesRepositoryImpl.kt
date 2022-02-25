package com.salvador.breakingbad.data.repository

import com.salvador.breakingbad.data.local.FavoriteCharacterDao
import com.salvador.breakingbad.data.local.dto.FavoriteCharacter
import com.salvador.breakingbad.domain.repository.FavoriteCharacterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoritesRepositoryImpl @Inject constructor(
    private val favoriteCharacterDao: FavoriteCharacterDao
) : FavoriteCharacterRepository{

    override fun getFavorites(): Flow<List<FavoriteCharacter>> {
       return favoriteCharacterDao.getFavorites()
    }

    override suspend fun getFavoriteByID(id: Int) : FavoriteCharacter? {
        return favoriteCharacterDao.getFavoriteById(id)
    }

    override suspend fun insertFavorite(favoriteCharacter: FavoriteCharacter) {
        return favoriteCharacterDao.insertFavorite(favoriteCharacter)
    }

    override suspend fun deleteFavorite(favoriteCharacter: FavoriteCharacter) {
        return favoriteCharacterDao.deleteFavorite(favoriteCharacter)
    }

}