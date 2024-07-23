package com.github.januprasad.paging3demo.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.github.januprasad.paging3demo.models.Quote
import com.github.januprasad.paging3demo.retrofit.QuoteAPI
import java.lang.Exception

class QuotePagingSource(private val quoteAPI: QuoteAPI) : PagingSource<Int, Quote>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Quote> {
        return try {
            val position = params.key ?: 1
            val response = quoteAPI.getQuotes(position)

            return LoadResult.Page(
                data = response.quotes,
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (position == response.total) null else position + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Quote>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

}