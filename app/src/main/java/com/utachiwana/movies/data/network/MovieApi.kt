package com.utachiwana.movies.data.network

import com.utachiwana.movies.data.model.MovieResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    companion object{
        val URL = "https://api.nytimes.com/svc/movies/v2/reviews/"
        val PAGE_SIZE = 20
    }

    @GET("all.json")
    suspend fun getMovies(@Query("offset") offset: Int = 0) : Response<MovieResult>
}

