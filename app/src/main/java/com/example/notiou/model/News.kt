package com.example.notiou.model

data class News (
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val source: String,
    val image: String?,
    val category: String,
    val language: String,
    val country: String,
    val published_at: String
)

data class ReqResData (
    val pagination: Pagin,
    val data: List<News>,
)

data class Pagin (
    val limit: Int,
    val offset: Int,
    val count: Int,
    val total: Long
)