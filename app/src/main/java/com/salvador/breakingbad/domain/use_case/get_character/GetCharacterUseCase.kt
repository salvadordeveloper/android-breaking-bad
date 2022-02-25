package com.salvador.breakingbad.domain.use_case.get_character

import com.salvador.breakingbad.common.Resource
import com.salvador.breakingbad.data.remote.dto.toCharacter
import com.salvador.breakingbad.domain.model.Character
import com.salvador.breakingbad.domain.repository.CharacterRespository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCharacterUseCase @Inject constructor(private val repository: CharacterRespository){
    operator fun invoke(characterId : String): Flow<Resource<Character>> = flow {
        try{
            emit(Resource.Loading<Character>())
            val characters = repository.getCharacterById(characterId).toCharacter()
            emit(Resource.Success<Character>(characters))
        }catch (e: HttpException){
            emit(Resource.Error<Character>(e.localizedMessage ?: "An unexpected error"))
        }catch (e : IOException){
            emit(Resource.Error<Character>("Couldn't reach server. Check your internet connection"))
        }
    }
}