package com.salvador.breakingbad.presentation.detail
import com.salvador.breakingbad.domain.model.Character

data class DetailState(
    val isLoading : Boolean = false,
    var character : Character? = null,
    var error : String = ""
)