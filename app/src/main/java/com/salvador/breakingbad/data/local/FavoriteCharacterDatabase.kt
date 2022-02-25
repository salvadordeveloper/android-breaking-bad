package com.salvador.breakingbad.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.salvador.breakingbad.data.local.dto.FavoriteCharacter

@Database(
    entities = [FavoriteCharacter::class],
    version = 1
)
abstract class FavoriteCharacterDatabase : RoomDatabase() {

    abstract val favoriteCharacterDao : FavoriteCharacterDao

    companion object{
        const val DATABASE_NAME = "favorites_db"
    }
}