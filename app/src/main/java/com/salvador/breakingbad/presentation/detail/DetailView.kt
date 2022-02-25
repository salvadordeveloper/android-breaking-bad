package com.salvador.breakingbad.presentation.detail

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.salvador.breakingbad.common.Orientation
import com.salvador.breakingbad.presentation.components.DetailsCharacter
import com.salvador.breakingbad.presentation.components.ImageCharacter

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DetailView(viewModel: DetailViewModel = hiltViewModel()) {

    val state = viewModel.state.value;
    val haptic = LocalHapticFeedback.current

    Box(modifier = Modifier.fillMaxSize()) {
        state.character?.let { character ->
            BoxWithConstraints {
                val orientation = remember { mutableStateOf(Orientation.Portrait) }
                orientation.value = if (maxWidth < maxHeight) Orientation.Portrait else Orientation.Landscape
                when(orientation.value){
                    Orientation.Portrait -> {
                        Column{
                            Box(
                                modifier = Modifier.fillMaxWidth().fillMaxHeight(0.7f),
                                contentAlignment = Alignment.BottomEnd
                            ) {
                                ImageCharacter(
                                    character = character,
                                    toggleFavorite = {
                                        viewModel.onEvent(EditEvent.ToggleFavorite)
                                        haptic.performHapticFeedback(
                                            HapticFeedbackType.LongPress
                                        )
                                    }
                                )
                            }
                            DetailsCharacter(character = character)
                        }
                    }
                    Orientation.Landscape ->{
                        Row(
                            verticalAlignment = Alignment.Top,
                            horizontalArrangement = Arrangement.Start
                        ){
                            Box(
                                modifier = Modifier.fillMaxWidth(.48f),
                            ) {
                                ImageCharacter(
                                    character = character,
                                    toggleFavorite = {
                                        viewModel.onEvent(EditEvent.ToggleFavorite)
                                        haptic.performHapticFeedback(
                                            HapticFeedbackType.LongPress
                                        )
                                    }
                                )
                            }
                            DetailsCharacter(character = character)
                        }
                    }
                }
            }
        }

        if(state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }

        if(state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}






