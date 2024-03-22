package com.auliame.githubuser.core.data.source.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity
data class UsersEntity(
    @PrimaryKey
    @ColumnInfo(name = "username")
    var username: String,
    @ColumnInfo(name = "avatar")
    var avatar: String = "",
    @ColumnInfo(name = "is_favorite")
    var isFavorite: Boolean = false
): Parcelable