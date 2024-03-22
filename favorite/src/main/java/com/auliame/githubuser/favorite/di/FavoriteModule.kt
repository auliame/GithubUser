package com.auliame.githubuser.favorite.di

import com.auliame.githubuser.favorite.ui.ListFavoriteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {
    viewModel { ListFavoriteViewModel(get()) }
}