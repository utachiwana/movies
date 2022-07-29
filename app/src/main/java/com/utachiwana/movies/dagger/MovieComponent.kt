package com.utachiwana.movies.dagger

import com.utachiwana.movies.main.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RetrofitModule::class])
interface MovieComponent {

    fun inject(mainActivity: MainActivity)

}