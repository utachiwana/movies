package com.utachiwana.movies

import android.app.Application
import android.content.Context
import com.utachiwana.movies.dagger.DaggerMovieComponent
import com.utachiwana.movies.dagger.MovieComponent

class MovieApp : Application() {

    val appComponent: MovieComponent by lazy {
        DaggerMovieComponent.create()
    }

}

val Context.appComponent: MovieComponent
get() = when (this) {
        is MovieApp -> appComponent
        else -> this.applicationContext.appComponent
    }