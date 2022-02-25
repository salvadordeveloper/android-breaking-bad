package com.salvador.breakingbad.domain.use_case.get_characters

import com.salvador.breakingbad.common.Resource
import com.salvador.breakingbad.data.remote.dto.toCharacter
import com.salvador.breakingbad.domain.repository.CharacterRespository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import retrofit2.HttpException
import javax.inject.Inject
import com.salvador.breakingbad.domain.model.Character
class GetCharactersUseCase @Inject constructor(
    private val respository: CharacterRespository
) {
    operator fun invoke(): Flow<Resource<List<Character>>> = flow {
        try{
            emit(Resource.Loading<List<Character>>())
            val characters = respository.getCharacters().map { it.toCharacter() }
            emit(Resource.Success<List<Character>>(characters))
        }catch (e: HttpException){
            emit(Resource.Error<List<Character>>(e.localizedMessage ?: "An unexpected error"))
        }catch (e : IOException){
            emit(Resource.Error<List<Character>>("Couldn't reach server. Check your internet connection"))
        }
    }
}