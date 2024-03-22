package com.auliame.githubuser.core.domain.repository

import com.auliame.githubuser.core.data.Resource
import com.auliame.githubuser.core.data.source.local.entity.UsersEntity
import com.auliame.githubuser.core.domain.model.UserDetailModel
import com.auliame.githubuser.core.domain.model.UserModel
import kotlinx.coroutines.flow.Flow

interface IUserRepository {
    fun getUsers(): Flow<Resource<List<UserModel>>>
    fun getUserDetail(userName: String): Flow<Resource<UserDetailModel>>
    fun getUserFollowers(userName: String): Flow<Resource<List<UserModel>>>
    fun getUserFollowing(userName: String): Flow<Resource<List<UserModel>>>
    fun getFavoriteUser(): Flow<List<UserModel>>
    fun updateFavoriteUser(userModel: UserModel, state: Boolean)
}