package com.salvador.breakingbad.presentation.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.salvador.breakingbad.domain.model.Character
import com.salvador.breakingbad.presentation.util.Screen
import com.salvador.breakingbad.presentation.components.GridList
import com.salvador.breakingbad.presentation.components.SearchBar

@OptIn(ExperimentalFoundationApi::class, ExperimentalComposeUiApi::class)
@Composable
fun HomeView(navController: NavController, viewModel: HomeViewModel = hiltViewModel(), showFavorites : Boolean = false) {

    val state = viewModel.state.value
    var searchText by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    fun actualList() : List<Character>{
        if (showFavorites) {
            return if (searchText.isNotEmpty()) {
                state.filteredFavoriteCharacters
            } else {
                state.favoriteCharacters
            }
        }
        return if(searchText.isNotEmpty()){
            state.filteredCharacters
        }else{
            state.characters
        }
    }

    Column {
        SearchBar(
            value = searchText,
            onValueChange = {
                searchText = it
                viewModel.onEvent(HomeEvent.FilterCharactersByText(searchText))
            },
            onDone = {
                focusManager.clearFocus()
            }
        )
        Box(contentAlignment = Alignment.Center){

            GridList(
                list = actualList(),
                onClickItem = { character ->
                    navController.navigate(Screen.DetailScreen.route + "/${character.char_id}")
                }
            )

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                if(state.error.isNotBlank()) {
                    Text(
                        text = state.error,
                        color = MaterialTheme.colors.error,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                    )
                }
                if(state.isLoading) {
                    CircularProgressIndicator()
                }
                if(actualList().isEmpty() && searchText.isNotEmpty()){
                    Text("No result")
                }
            }
        }
    }
}


