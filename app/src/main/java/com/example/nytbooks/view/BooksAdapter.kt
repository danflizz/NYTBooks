package com.example.nytbooks.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nytbooks.R
import com.example.nytbooks.databinding.BookItemBinding
import com.example.nytbooks.model.Book

class BooksAdapter(private var bookList: List<Book>) :
    RecyclerView.Adapter<BooksAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = BookItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.binding) {
            with(bookList[position]) {
                bookTitle.text = this.title
                bookDescription.text = this.description
                Glide.with(bookTitle.context).load(this.bookImage).error(R.drawable.generic_book).into(bookImg)
            }
        }
    }

    override fun getItemCount(): Int = bookList.size


    fun updateList(bookList: List<Book>) {
        this.bookList = bookList
        notifyDataSetChanged()
    }


    inner class ViewHolder(val binding: BookItemBinding) : RecyclerView.ViewHolder(binding.root)
}