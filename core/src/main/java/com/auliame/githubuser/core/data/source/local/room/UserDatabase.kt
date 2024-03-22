package com.auliame.githubuser.core.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.auliame.githubuser.core.data.source.local.entity.UsersEntity

@Database(entities = [UsersEntity::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
