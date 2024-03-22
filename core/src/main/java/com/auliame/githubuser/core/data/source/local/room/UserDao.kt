package com.auliame.githubuser.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.auliame.githubuser.core.data.source.local.entity.UsersEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(usersEntity: List<UsersEntity>)

    @Delete
    fun deleteUser(usersEntity: UsersEntity)

    @Query("SELECT * from usersentity")
    fun getAllUsers(): Flow<List<UsersEntity>>

    @Query("SELECT * from usersentity WHERE is_favorite = 1")
    fun getFavoriteUser(): Flow<List<UsersEntity>>

    @Update
    fun updateFavoriteUser(usersEntity: UsersEntity)
}