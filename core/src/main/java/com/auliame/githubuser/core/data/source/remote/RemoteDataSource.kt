package com.auliame.githubuser.core.data.source.remote

import com.auliame.githubuser.core.data.source.remote.network.ApiService
import com.auliame.githubuser.core.data.source.remote.response.ApiResponse
import com.auliame.githubuser.core.data.source.remote.response.user.User
import com.auliame.githubuser.core.data.source.remote.response.user.UsersItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(
    private val apiService: ApiService
) {
    suspend fun getUsers(): Flow<ApiResponse<List<UsersItem>>> =
        flow {
            try {
                val response = apiService.getUsers()
                if (response.isNotEmpty()){
                    emit(ApiResponse.Success(response))
                }else{
                    emit(ApiResponse.Empty)
                }
            }catch (e: Exception){
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)

    suspend fun getUserDetail(userName: String): Flow<ApiResponse<User>> =
        flow {
            try {
                val response = apiService.getUserDetail(userName)
                emit(ApiResponse.Success(response))
            }catch (e: Exception){
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)

    suspend fun getUserFollowers(userName: String): Flow<ApiResponse<List<UsersItem>>> =
        flow {
            try {
                val response = apiService.getUserFollowers(userName)
                if (response.isNotEmpty()){
                    emit(ApiResponse.Success(response))
                }else{
                    emit(ApiResponse.Empty)
                }
            }catch (e: Exception){
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)

    suspend fun getUserFollowing(userName: String): Flow<ApiResponse<List<UsersItem>>> =
        flow {
            try {
                val response = apiService.getUserFollowing(userName)
                if (response.isNotEmpty()){
                    emit(ApiResponse.Success(response))
                }else{
                    emit(ApiResponse.Empty)
                }
            }catch (e: Exception){
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
}