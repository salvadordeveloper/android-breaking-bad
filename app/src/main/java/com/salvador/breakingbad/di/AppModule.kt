package com.salvador.breakingbad.di

import android.app.Application
import androidx.room.Room
import com.google.gson.GsonBuilder
import com.salvador.breakingbad.common.Constants
import com.salvador.breakingbad.data.local.FavoriteCharacterDatabase
import com.salvador.breakingbad.data.remote.BreakingBadApi
import com.salvador.breakingbad.data.repository.CharactersRepositoryImpl
import com.salvador.breakingbad.data.repository.FavoritesRepositoryImpl
import com.salvador.breakingbad.domain.repository.CharacterRespository
import com.salvador.breakingbad.domain.repository.FavoriteCharacterRepository
import com.salvador.breakingbad.domain.use_case.favorites.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideBreakingBadAPI() : BreakingBadApi{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BreakingBadApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCharacterRepository(api: BreakingBadApi) : CharacterRespository{
        return CharactersRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): FavoriteCharacterDatabase {
        return Room.databaseBuilder(
            app,
            FavoriteCharacterDatabase::class.java,
            FavoriteCharacterDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideFavoriteCharacterRepository(db: FavoriteCharacterDatabase): FavoriteCharacterRepository{
        return FavoritesRepositoryImpl(db.favoriteCharacterDao)
    }

    @Provides
    @Singleton
    fun provideFavoritesUseCases(repository: FavoriteCharacterRepository): FavoritesUseCases {
        return FavoritesUseCases(
            getFavorite = GetFavorite(repository),
            getFavorites = GetFavorites(repository),
            deleteFavorite = DeleteFavorite(repository),
            addFavorite = AddFavorite(repository)
        )
    }

}