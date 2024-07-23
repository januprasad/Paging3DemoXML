package com.github.januprasad.paging3demo.models

data class Response(
    val limit: Int,
    val quotes: List<Quote>,
    val skip: Int,
    val total: Int
)