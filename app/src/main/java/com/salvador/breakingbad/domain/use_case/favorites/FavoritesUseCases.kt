package com.salvador.breakingbad.domain.use_case.favorites

data class FavoritesUseCases (
    val getFavorites : GetFavorites,
    val getFavorite : GetFavorite,
    val addFavorite : AddFavorite,
    val deleteFavorite: DeleteFavorite
)