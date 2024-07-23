package com.example.nytbooks.di

import com.example.nytbooks.view.BookActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class, PresenterModule::class])
interface AppComponent {
    fun inject(activity: BookActivity)
}