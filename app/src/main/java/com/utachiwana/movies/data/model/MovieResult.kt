package com.utachiwana.movies.data.model

import com.google.gson.annotations.SerializedName

data class MovieResult(
    val copyright: String,
    val has_more: Boolean,
    val num_results: Int,
    @SerializedName("results")
    val results: List<MovieClass>,
    val status: String
)