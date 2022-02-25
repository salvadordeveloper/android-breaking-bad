package com.salvador.breakingbad.presentation.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.salvador.breakingbad.common.Resource
import com.salvador.breakingbad.domain.use_case.get_characters.GetCharactersUseCase
import kotlinx.coroutines.flow.forEach
import javax.inject.Inject
import androidx.compose.runtime.State
import com.salvador.breakingbad.domain.use_case.favorites.FavoritesUseCases
import com.salvador.breakingbad.presentation.detail.EditEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val favoritesUseCases: FavoritesUseCases,
) : ViewModel() {

    private val _state = mutableStateOf(HomeState())
    val state: State<HomeState> = _state

    init {
        getCharacters()
    }

    fun onEvent(event: HomeEvent) {
        when (event){
            is HomeEvent.FilterCharactersByText -> {
                filterCharactersBy(event.text)
            }
        }
    }

    private fun filterCharactersBy(searchText : String){
        val search = searchText.lowercase()
        val characters = _state.value.characters.filter { character ->
            (character.name.lowercase().contains(search) || character.nickname.lowercase().contains(search) || character.portrayed.lowercase().contains(search))
        }
        val favoriteCharacters = _state.value.favoriteCharacters.filter { character ->
            (character.name.lowercase().contains(search) || character.nickname.lowercase().contains(search) || character.portrayed.lowercase().contains(search))
        }

        _state.value = _state.value.copy(filteredCharacters = characters, filteredFavoriteCharacters = favoriteCharacters)
    }

    private fun getFavorites(){
        favoritesUseCases.getFavorites().onEach { result->
            var favorites = _state.value.characters.filter { character -> result.any { it.name == character.name }}
            _state.value = _state.value.copy(favoriteCharacters = favorites)
        }.launchIn(viewModelScope)
    }

    private fun getCharacters(){
        getCharactersUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    result.data?.let{ characters ->
                        _state.value = HomeState(characters = characters)
                        getFavorites()
                    }
                }
                is Resource.Error -> {
                    _state.value = HomeState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = HomeState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}