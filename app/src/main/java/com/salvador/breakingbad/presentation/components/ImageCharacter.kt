package com.salvador.breakingbad.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.salvador.breakingbad.domain.model.Character


@Composable
fun ImageCharacter(
    character : Character,
    toggleFavorite : () -> Unit
) {
    Box(
        contentAlignment = Alignment.BottomEnd
    ) {
        Image(painter = rememberImagePainter(character.img), contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectTapGestures(
                        onDoubleTap = {
                            toggleFavorite()
                        }
                    )
                },
            contentScale = ContentScale.Crop,
            alignment = Alignment.TopCenter,
        )
        Box(modifier = Modifier.padding(15.dp)){
            FavoriteButton(isFavorite = character.isFavorite, onItemClick = {
                toggleFavorite()
            })
        }
    }
}