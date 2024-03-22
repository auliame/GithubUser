package com.auliame.githubuser

import android.app.Application
import com.auliame.githubuser.core.di.databaseModule
import com.auliame.githubuser.core.di.networkModule
import com.auliame.githubuser.core.di.repositoryModule
import com.auliame.githubuser.di.useCaseModule
import com.auliame.githubuser.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class GithubUserApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@GithubUserApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}