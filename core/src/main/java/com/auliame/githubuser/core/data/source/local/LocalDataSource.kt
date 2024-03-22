package com.auliame.githubuser.core.data.source.local

import com.auliame.githubuser.core.data.source.local.entity.UsersEntity
import com.auliame.githubuser.core.data.source.local.room.UserDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val userDao: UserDao) {
    suspend fun insertUser(usersEntity: List<UsersEntity>) = userDao.insertUser(usersEntity)

    fun deleteUser(usersEntity: UsersEntity) = userDao.deleteUser(usersEntity)

    fun getAllUsers(): Flow<List<UsersEntity>> = userDao.getAllUsers()

    fun getFavoriteUser(): Flow<List<UsersEntity>> = userDao.getFavoriteUser()

    fun updateFavoriteUser(usersEntity: UsersEntity, newState: Boolean){
        usersEntity.isFavorite = newState
        userDao.updateFavoriteUser(usersEntity)
    }
}