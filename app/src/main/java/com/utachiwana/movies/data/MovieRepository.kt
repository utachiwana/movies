package com.utachiwana.movies.data

import androidx.lifecycle.LiveData
import androidx.paging.*
import com.utachiwana.movies.data.model.MovieClass
import com.utachiwana.movies.data.network.MovieApi
import com.utachiwana.movies.data.network.MovieSource
import javax.inject.Inject

class MovieRepository @Inject constructor(private val api : MovieApi) {
    suspend fun moviePagingSource() : LiveData<PagingData<MovieClass>> = Pager(
        config = PagingConfig(pageSize = MovieApi.PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = { MovieSource(api) }
    ).liveData
}
