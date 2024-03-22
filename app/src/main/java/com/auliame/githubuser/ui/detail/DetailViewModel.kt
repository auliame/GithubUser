package com.auliame.githubuser.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.auliame.githubuser.core.domain.model.UserModel
import com.auliame.githubuser.core.domain.usecase.UserUseCase

class DetailViewModel(private val userUseCase: UserUseCase): ViewModel(){
    fun getUserDetail(username: String) = userUseCase.getUserDetail(username).asLiveData()
    fun updateFavoriteUser(userModel: UserModel, state: Boolean) =
        userUseCase.updateFavoriteUser(userModel, state)
}