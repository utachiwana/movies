package com.utachiwana.movies.data.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.utachiwana.movies.data.model.MovieClass
import com.utachiwana.movies.data.network.MovieApi
import retrofit2.HttpException
import javax.inject.Inject

class MovieSource @Inject constructor(private val api: MovieApi) :
    PagingSource<Int, MovieClass>() {

    override fun getRefreshKey(state: PagingState<Int, MovieClass>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val anchorPage = state.closestPageToPosition(anchorPosition) ?: return null
        return anchorPage.prevKey?.plus(1) ?: anchorPage.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieClass> {
        val pageNumber = params.key ?: 0
        val offset = MovieApi.PAGE_SIZE * pageNumber
        val response = api.getMovies(offset)
        return try {
            if (response.isSuccessful) {
                val movies = response.body()!!.results
                val nextPageNumber = if (movies.isEmpty()) null else pageNumber + 1
                val prevPageNumber = if (pageNumber > 1) pageNumber - 1 else null
                LoadResult.Page(movies, prevPageNumber, nextPageNumber)
            } else {
                LoadResult.Error(HttpException(response))
            }
        } catch (e: HttpException) {
            LoadResult.Error(e)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}