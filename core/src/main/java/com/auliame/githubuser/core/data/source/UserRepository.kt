package com.auliame.githubuser.core.data.source

import com.auliame.githubuser.core.data.NetworkBoundResource
import com.auliame.githubuser.core.data.Resource
import com.auliame.githubuser.core.data.source.local.LocalDataSource
import com.auliame.githubuser.core.data.source.remote.RemoteDataSource
import com.auliame.githubuser.core.data.source.remote.response.ApiResponse
import com.auliame.githubuser.core.data.source.remote.response.user.User
import com.auliame.githubuser.core.data.source.remote.response.user.UsersItem
import com.auliame.githubuser.core.domain.model.UserDetailModel
import com.auliame.githubuser.core.domain.model.UserModel
import com.auliame.githubuser.core.domain.repository.IUserRepository
import com.auliame.githubuser.core.utils.AppExecutors
import com.auliame.githubuser.core.utils.mapToDomain
import com.auliame.githubuser.core.utils.listUsersItemMapToDomain
import com.auliame.githubuser.core.utils.mapToEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserRepository (
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
): IUserRepository{
    override fun getUsers(): Flow<Resource<List<UserModel>>> =
        object : NetworkBoundResource<List<UserModel>, List<UsersItem>>(){
            override fun loadFromDb(): Flow<List<UserModel>> {
                return localDataSource.getAllUsers().map {
                    it.mapToDomain()
                }
            }

            override suspend fun createCall(): Flow<ApiResponse<List<UsersItem>>> =
                remoteDataSource.getUsers()

            override suspend fun saveCallResult(data: List<UsersItem>) {
                val users = data.mapToEntity()
                localDataSource.insertUser(users)
            }

            override fun shouldFetch(data: List<UserModel>?): Boolean =
                data == null || data.isEmpty()
        }.asFlow()

    override fun getUserDetail(userName: String): Flow<Resource<UserDetailModel>> =
        object : NetworkResource<UserDetailModel, User>(){
            override fun convertResponseToModel(response: User): UserDetailModel =
                response.mapToDomain()

            override suspend fun createCall(): Flow<ApiResponse<User>> =
                remoteDataSource.getUserDetail(userName)

        }.asFlow()


    override fun getUserFollowers(userName: String): Flow<Resource<List<UserModel>>> =
        object : NetworkResource<List<UserModel>, List<UsersItem>>() {
            override fun convertResponseToModel(response: List<UsersItem>): List<UserModel> =
                response.listUsersItemMapToDomain()

            override suspend fun createCall(): Flow<ApiResponse<List<UsersItem>>> =
                remoteDataSource.getUserFollowers(userName)

        }.asFlow()

    override fun getUserFollowing(userName: String): Flow<Resource<List<UserModel>>> =
        object : NetworkResource<List<UserModel>, List<UsersItem>>() {
            override fun convertResponseToModel(response: List<UsersItem>): List<UserModel> =
                response.listUsersItemMapToDomain()

            override suspend fun createCall(): Flow<ApiResponse<List<UsersItem>>> =
                remoteDataSource.getUserFollowing(userName)

        }.asFlow()


    override fun getFavoriteUser(): Flow<List<UserModel>> =
        localDataSource.getFavoriteUser().map {
            it.mapToDomain()
        }

    override fun updateFavoriteUser(userModel: UserModel, state: Boolean) {
        val usersEntity = userModel.mapToEntity()
        appExecutors.diskIO().execute{localDataSource.updateFavoriteUser(usersEntity, state)}
    }


}