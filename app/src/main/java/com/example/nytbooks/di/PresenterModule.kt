package com.example.nytbooks.di

import com.example.nytbooks.presenter.BooksContract
import com.example.nytbooks.presenter.BooksPresenter
import com.example.nytbooks.network.NYTimesService
import dagger.Module
import dagger.Provides

@Module
class PresenterModule {
    @Provides
    fun provideBooksPresenter(apiService: NYTimesService): BooksContract.Presenter {
        return BooksPresenter(apiService)
    }
}