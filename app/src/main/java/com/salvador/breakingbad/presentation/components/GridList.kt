package com.salvador.breakingbad.presentation.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.salvador.breakingbad.domain.model.Character

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GridList(
    list : List<Character>,
    onClickItem : (Character) -> Unit
) {
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 50.dp),
        cells = GridCells.Adaptive(160.dp),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(list) { character: Character ->
           CharacterItem(character = character, onClick = onClickItem)
        }
    }
}

@Composable
fun CharacterItem(
    character : Character,
    onClick: (Character) -> Unit
) {
    Card(
        modifier = Modifier
            .height(250.dp)
            .padding(5.dp)
            .clickable {
                onClick(character)
            },
        elevation = 10.dp,
        backgroundColor = MaterialTheme.colors.primary
    ) {
        Box(contentAlignment = Alignment.BottomCenter){
            Image(
                painter = rememberImagePainter(
                    data = character.img,
                    builder = {
                        crossfade(true)
                    }
                ),
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
            )
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(35.dp)
                    .background(
                        Color.Black.copy(alpha = 0.5f),
                    )
            ){
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text=character.name,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    color = Color.White, fontSize = 17.sp)
            }
        }
    }
}