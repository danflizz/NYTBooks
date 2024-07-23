package com.example.nytbooks

import android.app.Application
import com.example.nytbooks.di.AppComponent
import com.example.nytbooks.di.DaggerAppComponent
import com.example.nytbooks.di.NetworkModule
import com.example.nytbooks.di.PresenterModule

class BaseApp : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .networkModule(NetworkModule())
            .presenterModule(PresenterModule())
            .build()
    }
}