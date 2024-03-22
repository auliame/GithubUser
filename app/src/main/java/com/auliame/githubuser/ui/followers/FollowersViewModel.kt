package com.auliame.githubuser.ui.followers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.auliame.githubuser.core.domain.usecase.UserUseCase

class FollowersViewModel(
    private val userUseCase: UserUseCase
): ViewModel() {
    fun getUserFollowers(userName: String) = userUseCase.getUserFollowers(userName).asLiveData()
}