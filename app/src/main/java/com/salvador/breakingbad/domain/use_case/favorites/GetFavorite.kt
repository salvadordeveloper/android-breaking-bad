package com.salvador.breakingbad.domain.use_case.favorites

import com.salvador.breakingbad.data.local.dto.FavoriteCharacter
import com.salvador.breakingbad.domain.repository.FavoriteCharacterRepository
import javax.inject.Inject

class GetFavorite @Inject constructor(private val repository : FavoriteCharacterRepository) {

    suspend operator fun invoke(id : Int) : FavoriteCharacter?{
       return repository.getFavoriteByID(id)
    }
}