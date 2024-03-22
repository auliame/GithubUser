package com.auliame.githubuser.core.data.source

import com.auliame.githubuser.core.data.Resource
import com.auliame.githubuser.core.data.source.remote.response.ApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

abstract class NetworkResource<ResultType, RequestType> {

    private val result: Flow<Resource<ResultType>> = flow {
        emit(Resource.Loading())
        when (val apiResponse = createCall().first()) {
            is ApiResponse.Success -> {
                emit(Resource.Success(convertResponseToModel(apiResponse.data)))
                onFetchSuccess()
            }
            is ApiResponse.Error -> {
                emit(
                    Resource.Error<ResultType>(
                        apiResponse.errorMessage
                    )
                )
            }
            ApiResponse.Empty -> {
                emit(Resource.Error("Empty Data"))
            }
        }
    }

    protected abstract fun convertResponseToModel(response: RequestType): ResultType

    protected abstract suspend fun createCall(): Flow<ApiResponse<RequestType>>

    protected open suspend fun onFetchSuccess() {}

    fun asFlow() = result

}