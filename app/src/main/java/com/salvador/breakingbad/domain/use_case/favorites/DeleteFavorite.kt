package com.salvador.breakingbad.domain.use_case.favorites

import com.salvador.breakingbad.data.local.dto.FavoriteCharacter
import com.salvador.breakingbad.domain.repository.FavoriteCharacterRepository
import javax.inject.Inject

class DeleteFavorite @Inject constructor(private val repository : FavoriteCharacterRepository) {

    suspend operator fun invoke(favorite: FavoriteCharacter){
        repository.deleteFavorite(favorite)
    }
}