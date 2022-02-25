package com.salvador.breakingbad.presentation.home

sealed class HomeEvent {
    data class FilterCharactersByText(val text : String) : HomeEvent()
}