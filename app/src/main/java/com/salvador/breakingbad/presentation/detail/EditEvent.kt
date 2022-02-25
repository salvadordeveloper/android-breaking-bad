package com.salvador.breakingbad.presentation.detail

sealed class EditEvent {
   object ToggleFavorite : EditEvent()
}