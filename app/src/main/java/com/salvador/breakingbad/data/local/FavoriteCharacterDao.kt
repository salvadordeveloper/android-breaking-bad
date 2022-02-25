package com.salvador.breakingbad.data.local

import androidx.room.*
import com.salvador.breakingbad.data.local.dto.FavoriteCharacter
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteCharacterDao{

        @Query("SELECT * FROM favorites")
        fun getFavorites() : Flow<List<FavoriteCharacter>>

        @Query("SELECT * FROM favorites WHERE id = :id")
        suspend fun getFavoriteById(id : Int) : FavoriteCharacter?

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insertFavorite(favorite : FavoriteCharacter)

        @Delete
        suspend fun deleteFavorite(favorite : FavoriteCharacter)
}