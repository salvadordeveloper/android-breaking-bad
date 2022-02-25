package com.salvador.breakingbad.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@Composable
fun SearchBar(
    value: String,
    onValueChange: (String) -> Unit,
    onDone: KeyboardActionScope.() -> Unit
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        leadingIcon = { Icon(Icons.Filled.Search, null, tint = if(value.isNotEmpty()) MaterialTheme.colors.secondaryVariant else Color.Gray) },
        modifier = Modifier
            .background(MaterialTheme.colors.primary)
            .padding(horizontal = 10.dp, vertical = 10.dp)
            .fillMaxWidth()
            .background(MaterialTheme.colors.secondary),
        colors = TextFieldDefaults.textFieldColors(
            focusedLabelColor = MaterialTheme.colors.secondaryVariant.copy(alpha = 0.7f),
            cursorColor = MaterialTheme.colors.secondaryVariant,
            textColor = MaterialTheme.colors.secondaryVariant,
            placeholderColor = MaterialTheme.colors.secondaryVariant,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            backgroundColor = Color.Transparent,
        ),
        label = { Text("Search character...") },
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(
            onDone = onDone

        ),
    )
}