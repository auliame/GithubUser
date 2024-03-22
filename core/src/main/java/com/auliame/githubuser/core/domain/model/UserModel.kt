package com.auliame.githubuser.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserModel(
    val username: String,
    val avatarUrl: String,
    val isFavorite: Boolean
): Parcelable
