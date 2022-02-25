package com.salvador.breakingbad.presentation.home

import com.salvador.breakingbad.data.local.dto.FavoriteCharacter
import com.salvador.breakingbad.domain.model.Character

data class HomeState(
    val isLoading: Boolean = false,
    val characters: List<Character> = emptyList(),
    val filteredCharacters: List<Character> = emptyList(),
    val favoriteCharacters : List<Character> = emptyList(),
    val filteredFavoriteCharacters : List<Character> = emptyList(),
    val error: String = ""
)