package com.example.nytbooks.network

import com.example.nytbooks.model.NYTimesBookResponse
import retrofit2.http.GET

interface NYTimesService {

    @GET("lists/current/hardcover-fiction.json?api-key=KoRB4K5LRHygfjCL2AH6iQ7NeUqDAGAB&offset=0")
    suspend fun getNYTimesBooks(): NYTimesBookResponse

}