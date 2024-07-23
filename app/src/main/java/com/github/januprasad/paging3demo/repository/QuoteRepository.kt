package com.github.januprasad.paging3demo.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.github.januprasad.paging3demo.paging.QuotePagingSource
import com.github.januprasad.paging3demo.retrofit.QuoteAPI
import javax.inject.Inject

class QuoteRepository @Inject constructor(private val quoteAPI: QuoteAPI) {

    fun getQuotes() = Pager(
        config = PagingConfig(pageSize = 20, maxSize = 100),
        pagingSourceFactory = { QuotePagingSource(quoteAPI) }
    ).liveData
}