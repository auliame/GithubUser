package com.auliame.githubuser.favorite.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.auliame.githubuser.core.domain.usecase.UserUseCase

class ListFavoriteViewModel(
    private val userUseCase: UserUseCase
): ViewModel() {
    fun getFavoriteUser() = userUseCase.getFavoriteUser().asLiveData()
}