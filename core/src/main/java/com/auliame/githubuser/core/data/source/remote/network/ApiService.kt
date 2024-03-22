package com.auliame.githubuser.core.data.source.remote.network

import com.auliame.githubuser.core.data.source.remote.response.user.User
import com.auliame.githubuser.core.data.source.remote.response.user.UsersItem
import com.auliame.githubuser.core.utils.API_KEY
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface ApiService {
    @Headers("Authorization: $API_KEY")
    @GET("/users")
    suspend fun getUsers(): List<UsersItem>

    @Headers("Authorization: $API_KEY")
    @GET("/users/{username}")
    suspend fun getUserDetail(
        @Path("username") userName: String
    ): User

    @Headers("Authorization: $API_KEY")
    @GET("/users/{username}/followers")
    suspend fun getUserFollowers(
        @Path("username") userName: String
    ): List<UsersItem>

    @Headers("Authorization: $API_KEY")
    @GET("/users/{username}/following")
    suspend fun getUserFollowing(
        @Path("username") userName: String
    ): List<UsersItem>
}