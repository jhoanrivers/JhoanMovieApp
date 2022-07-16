package com.example.jhoanmovie.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class APIResponse (
    val page: Long? = null,
    val results: List<Movie>? = null,

    @SerialName("total_pages")
    val totalPages: Long? = null,

    @SerialName("total_results")
    val totalResults: Long? = null
)