package com.auliame.githubuser.core.domain.usecase

import com.auliame.githubuser.core.data.Resource
import com.auliame.githubuser.core.domain.model.UserDetailModel
import com.auliame.githubuser.core.domain.model.UserModel
import com.auliame.githubuser.core.domain.repository.IUserRepository
import kotlinx.coroutines.flow.Flow

class UserInteractor(
    private val repository: IUserRepository
): UserUseCase {
    override fun getUsers(): Flow<Resource<List<UserModel>>> = repository.getUsers()

    override fun getUserDetail(userName: String): Flow<Resource<UserDetailModel>> =
        repository.getUserDetail(userName)

    override fun getUserFollowers(userName: String): Flow<Resource<List<UserModel>>> =
        repository.getUserFollowers(userName)

    override fun getUserFollowing(userName: String): Flow<Resource<List<UserModel>>> =
        repository.getUserFollowing(userName)

    override fun getFavoriteUser(): Flow<List<UserModel>> =
        repository.getFavoriteUser()

    override fun updateFavoriteUser(userModel: UserModel, state: Boolean) =
        repository.updateFavoriteUser(userModel, state)
}