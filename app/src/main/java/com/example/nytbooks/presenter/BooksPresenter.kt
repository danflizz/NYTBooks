package com.example.nytbooks.presenter

import com.example.nytbooks.network.NYTimesService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BooksPresenter(private val nyTimesService: NYTimesService) : BooksContract.Presenter {

    private var view: BooksContract.View? = null
    private val job = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + job)

    override fun attachView(view: BooksContract.View) {
        this.view = view
    }

    override fun detachView() {
        view = null
        job.cancel()
    }

    override fun fetchBooks() {
        coroutineScope.launch {
            try {
                val response = withContext(Dispatchers.IO) { //IO Dispatcher to perform network operation
                    nyTimesService.getNYTimesBooks()
                }
                view?.showBookList(response.results.books)
            } catch (e: Exception) {
                view?.showError(e.message ?: "An unknown error occurred")
            }
        }
    }
}