package com.auliame.githubuser.ui.following

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.auliame.githubuser.core.domain.usecase.UserUseCase

class FollowingViewModel(
    private val userUseCase: UserUseCase
): ViewModel() {
    fun getUserFollowing(userName: String) = userUseCase.getUserFollowing(userName).asLiveData()
}