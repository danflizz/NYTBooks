package com.example.nytbooks

import com.example.nytbooks.model.Book
import com.example.nytbooks.model.BookList
import com.example.nytbooks.model.NYTimesBookResponse
import com.example.nytbooks.network.NYTimesService
import com.example.nytbooks.presenter.BooksContract
import com.example.nytbooks.presenter.BooksPresenter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import retrofit2.HttpException

@ExperimentalCoroutinesApi
class BooksPresenterTest {
    @Mock
    private lateinit var mockView: BooksContract.View

    @Mock
    private lateinit var mockApiService: NYTimesService

    private lateinit var presenter: BooksPresenter

    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        presenter = BooksPresenter(mockApiService)
        presenter.attachView(mockView)
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        presenter.detachView()
        Dispatchers.resetMain() // reset the main dispatcher to the original Main dispatcher
        testDispatcher.cleanupTestCoroutines()
    }


    @Test
    fun `fetchBooks success`() = runTest(StandardTestDispatcher()) {
        val scope = CoroutineScope(StandardTestDispatcher(testScheduler))
        val books = listOf(
            Book(
                description = "Description",
                title = "A DEATH IN CORNWALL",
                bookImage = "https://storage.googleapis.com/du-prd/books/images/9780063384200.jpg"
            )
        )
        scope.launch {

            val response = NYTimesBookResponse(results = BookList(books = books))

            `when`(mockApiService.getNYTimesBooks()).thenReturn(response)

            // When
            presenter.fetchBooks()

            // Then
            verify(mockView).showBookList(books)
        }
    }

    @Test
    fun `fetchBooks failure`() = runTest(StandardTestDispatcher()) {
        val scope = CoroutineScope(StandardTestDispatcher(testScheduler))

        scope.launch {
            // Given
            val exception = mock(HttpException::class.java)
            `when`(mockApiService.getNYTimesBooks()).thenThrow(exception)

            // When
            presenter.fetchBooks()
            // Then
            verify(mockView).showError(anyString())
        }

    }

}