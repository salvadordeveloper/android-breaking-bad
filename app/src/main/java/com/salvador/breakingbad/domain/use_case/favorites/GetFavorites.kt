package com.salvador.breakingbad.domain.use_case.favorites

import com.salvador.breakingbad.data.local.dto.FavoriteCharacter
import com.salvador.breakingbad.domain.repository.FavoriteCharacterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavorites @Inject constructor(private val repository : FavoriteCharacterRepository) {


   operator fun invoke() : Flow<List<FavoriteCharacter>> {
        return repository.getFavorites()
    }
}