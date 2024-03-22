package com.auliame.githubuser.core.utils

import com.auliame.githubuser.core.data.source.local.entity.UsersEntity
import com.auliame.githubuser.core.data.source.remote.response.user.User
import com.auliame.githubuser.core.data.source.remote.response.user.UsersItem
import com.auliame.githubuser.core.domain.model.UserDetailModel
import com.auliame.githubuser.core.domain.model.UserModel

fun List<UsersItem>.mapToEntity(): List<UsersEntity> {
    val userList = ArrayList<UsersEntity>()
    this.map {
        val user = UsersEntity(
            username = it.login,
            avatar = it.avatar_url,
            isFavorite = false
        )
        userList.add(user)
    }
    return userList
}

fun List<UsersItem>.listUsersItemMapToDomain(): List<UserModel> {
    val userList = mutableListOf<UserModel>()
    this.map {
        val user = UserModel(
            username = it.login,
            avatarUrl = it.avatar_url,
            isFavorite = false
        )
        userList.add(user)
    }
    return userList
}

fun List<UsersEntity>.mapToDomain(): List<UserModel> =
    this.map {
        UserModel(
            username = it.username,
            avatarUrl = it.avatar,
            isFavorite = it.isFavorite
        )
    }

fun UserModel.mapToEntity(): UsersEntity =
    UsersEntity(
        username = this.username,
        avatar = this.avatarUrl,
        isFavorite = this.isFavorite
    )

fun User.mapToDomain(): UserDetailModel =
    UserDetailModel(
        username = this.login,
        name = this.name,
        avatarUrl = this.avatar_url,
        following = this.following,
        followers = this.followers
    )