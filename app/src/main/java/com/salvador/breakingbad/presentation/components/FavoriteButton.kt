package com.salvador.breakingbad.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun FavoriteButton(isFavorite : Boolean, onItemClick: () -> Unit) {
    Box(
        modifier = Modifier
            .clip(CircleShape)
            .background(color = MaterialTheme.colors.background)
            .height(70.dp)
            .width(70.dp)
            .clickable { onItemClick() },
        contentAlignment = Alignment.Center
    ){
        Icon(
            imageVector = Icons.Default.Favorite,
            contentDescription = "Favorite",
            tint = if(isFavorite)  Color.Red else Color.Gray,
            modifier = Modifier
                .height(30.dp)
                .width(30.dp)
        )
    }
}