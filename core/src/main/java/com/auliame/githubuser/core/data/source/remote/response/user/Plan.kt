package com.auliame.githubuser.core.data.source.remote.response.user

data class Plan(
    val collaborators: Int,
    val name: String,
    val private_repos: Int,
    val space: Int
)