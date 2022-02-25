package com.salvador.breakingbad.data.local.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
class FavoriteCharacter (
    val name : String,
    @PrimaryKey val id: Int? = null
)

class InvalidFavoriteException(message: String): Exception(message)