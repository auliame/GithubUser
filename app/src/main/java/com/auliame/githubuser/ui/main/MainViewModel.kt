package com.auliame.githubuser.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.auliame.githubuser.core.domain.usecase.UserUseCase

class MainViewModel(private val userUseCase: UserUseCase): ViewModel() {
    fun getUsers() = userUseCase.getUsers().asLiveData()
}