package com.example.nytbooks.model

import com.google.gson.annotations.SerializedName

data class NYTimesBookResponse(val results: BookList)

data class BookList(val books: List<Book>)

data class Book(
    val description: String,
    val title: String,
    @SerializedName("book_image")
    val bookImage: String
)