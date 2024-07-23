package com.example.nytbooks.presenter

import com.example.nytbooks.model.Book

/**
 * Defines the contract between the View and the Presenter
 */
interface BooksContract {
    interface View {
        fun showBookList(books: List<Book>)
        fun showError(message: String)
    }

    interface Presenter {
        fun attachView(view : View)
        fun detachView()
        fun fetchBooks()
    }
}