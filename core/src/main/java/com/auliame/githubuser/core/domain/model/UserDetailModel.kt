package com.auliame.githubuser.core.domain.model

data class UserDetailModel(
    val username: String,
    val name: String,
    val avatarUrl: String,
    val following: Int,
    val followers: Int
)
