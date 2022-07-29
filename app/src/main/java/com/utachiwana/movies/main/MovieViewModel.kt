package com.utachiwana.movies.main

import androidx.lifecycle.*
import androidx.paging.*
import com.utachiwana.movies.data.MovieRepository
import com.utachiwana.movies.data.model.MovieClass
import javax.inject.Inject

class MovieViewModel @Inject constructor(private val rep: MovieRepository) : ViewModel() {

    suspend fun getMovies(): LiveData<PagingData<MovieClass>> =
        rep.moviePagingSource().cachedIn(viewModelScope)

    class Factory @Inject constructor(private val rep: MovieRepository) :
        ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MovieViewModel(rep) as T
        }
    }

}