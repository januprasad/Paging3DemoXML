package com.github.januprasad.paging3demo.retrofit

import com.github.januprasad.paging3demo.models.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface QuoteAPI {

    @GET("/quotes")
    suspend fun getQuotes(@Query("10") limit: Int): Response
}