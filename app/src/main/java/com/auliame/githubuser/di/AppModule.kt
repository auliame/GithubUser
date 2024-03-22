package com.auliame.githubuser.di

import com.auliame.githubuser.core.domain.usecase.UserInteractor
import com.auliame.githubuser.core.domain.usecase.UserUseCase
import com.auliame.githubuser.ui.detail.DetailViewModel
import com.auliame.githubuser.ui.followers.FollowersViewModel
import com.auliame.githubuser.ui.following.FollowingViewModel
import com.auliame.githubuser.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<UserUseCase>{ UserInteractor(get()) }
}

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { DetailViewModel(get()) }
    viewModel { FollowersViewModel(get()) }
    viewModel { FollowingViewModel(get()) }
}