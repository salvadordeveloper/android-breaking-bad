package com.salvador.breakingbad.presentation.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.salvador.breakingbad.common.Constants
import com.salvador.breakingbad.common.Resource
import com.salvador.breakingbad.data.local.dto.FavoriteCharacter
import com.salvador.breakingbad.data.local.dto.InvalidFavoriteException
import com.salvador.breakingbad.domain.use_case.favorites.FavoritesUseCases
import com.salvador.breakingbad.domain.use_case.get_character.GetCharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.salvador.breakingbad.domain.model.Character

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val favoritesUseCases: FavoritesUseCases,
    private val getCharacterUseCase: GetCharacterUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel(){

    private val _state = mutableStateOf(DetailState())
    val state: State<DetailState> = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_CHARACTER_ID)?.let { characterId ->
            getCharacter(characterId)
        }
    }

    fun onEvent(event: EditEvent){
        when (event){
            is EditEvent.ToggleFavorite -> {
                _state.value.character?.let { character ->
                    if (character.isFavorite)
                        _state.value =
                            DetailState(character = character.copy(isFavorite = false))
                    else {
                        _state.value =
                            DetailState(character = character.copy(isFavorite = true))
                    }
                    saveFavorite()
                }
            }
        }
    }

    private fun saveFavorite(){
        viewModelScope.launch {
            try{
                _state.value.character?.let { character ->
                    favoritesUseCases.getFavorite(character.char_id)?.let { favorite ->
                        if(!character.isFavorite) favoritesUseCases.deleteFavorite(favorite)
                    }?:run{
                        if(character.isFavorite) favoritesUseCases
                            .addFavorite(FavoriteCharacter(name = character.name, id = character.char_id))
                    }
                }
            }catch (e : InvalidFavoriteException){
                _state.value = _state.value.copy(error = "Error on favorite save")
            }
        }
    }

    private fun getCharacter(characterId : String){
        getCharacterUseCase(characterId).onEach { result ->
            when(result) {
                is Resource.Success -> {
                    result.data?.let { character ->
                        loadCharacter(character)
                    }
                }
                is Resource.Error -> {
                    _state.value =
                        DetailState(error = result.message ?: "An unexpected error occurred")
                }
                is Resource.Loading -> {
                    _state.value = DetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
    private fun loadCharacter(character: Character){
        viewModelScope.launch {
            favoritesUseCases.getFavorite(character.char_id)?.let {
                _state.value = DetailState(character = character.copy(isFavorite = true))
            }?:run{
                _state.value = DetailState(character = character)
            }
        }

    }



}