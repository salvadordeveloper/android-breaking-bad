package com.salvador.breakingbad.presentation.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.salvador.breakingbad.domain.model.Character

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DetailsCharacter(
    character: Character
) {
    LazyColumn(modifier = Modifier
        .fillMaxSize()
    ) {
        stickyHeader {
            DetailHeader(title = "Name")
        }
        item {
            DetailDescription(character.name)
        }
        stickyHeader {
            DetailHeader(title = "Occupations")
        }
        items(character.occupation){ c ->
            DetailDescription(c)
        }
        stickyHeader {
            DetailHeader(title = "Status")
        }
        item {
            DetailDescription(character.status)
        }
        stickyHeader {
            DetailHeader(title = "Seasons")
        }
        item {
            DetailDescription("Seasons")
        }
        stickyHeader {
            DetailHeader(title = "Nickname")
        }
        item {
            DetailDescription(character.nickname)
        }
    }
}

@Composable
fun DetailHeader(title : String) {
    Surface(elevation = 4.dp, color = MaterialTheme.colors.primary) {
        Text(title,
            modifier = Modifier
                .background(MaterialTheme.colors.primary)
                .padding(8.dp)
                .padding(start = 5.dp)
                .fillMaxWidth(),
            color = Color.White
        )
    }
}

@Composable
fun DetailDescription(description : String){
    Text(
        description,
        modifier = Modifier
            .padding(10.dp)
            .padding(start = 10.dp),
        color = MaterialTheme.colors.secondaryVariant
    )
}

