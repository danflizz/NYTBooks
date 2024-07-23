package com.example.nytbooks.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nytbooks.BaseApp
import com.example.nytbooks.R
import com.example.nytbooks.databinding.ActivityMainBinding
import com.example.nytbooks.model.Book
import com.example.nytbooks.presenter.BooksContract
import javax.inject.Inject

class BookActivity : AppCompatActivity(), BooksContract.View {

    @Inject
    lateinit var presenter: BooksContract.Presenter
    private lateinit var binding: ActivityMainBinding
    private lateinit var booksAdapter: BooksAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        binding.toolbar.title = getString(R.string.app_title)

        (application as BaseApp?)?.appComponent?.inject(this)

        with(binding) {
            bookRv.layoutManager = LinearLayoutManager(this@BookActivity)
            bookRv.addItemDecoration(DividerItemDecoration(this@BookActivity, RecyclerView.VERTICAL))
            booksAdapter = BooksAdapter(emptyList())
            bookRv.adapter = booksAdapter
        }

        presenter.attachView(this)

        fetchBooks()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.refresh_menu, menu) // menu for better accessibility and give user the option to update the list
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_refresh -> {
                fetchBooks()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }

    }

    private fun fetchBooks() {
        showLoading()
        presenter.fetchBooks()
    }

    override fun showBookList(books: List<Book>) {
        hideLoading()
        booksAdapter.updateList(books)
    }

    override fun showError(message: String) {
        with(binding) {
            msgTv.text = message
            errorContainer.visibility = View.VISIBLE
            bookRv.visibility = View.GONE
            tryAgainBtn.visibility = View.VISIBLE

            tryAgainBtn.setOnClickListener {
                fetchBooks()
            }

        }
    }

    private fun showLoading() {
        with(binding) { // a circular progress bar could be fancy but didn't get the time to do that
            errorContainer.visibility = View.VISIBLE
            msgTv.text = getString(R.string.loading)
            bookRv.visibility = View.GONE
        }
    }

    private fun hideLoading() {
        with(binding) {
            errorContainer.visibility = View.GONE
            bookRv.visibility = View.VISIBLE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }
}