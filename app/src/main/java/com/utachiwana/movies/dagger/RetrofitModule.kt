package com.utachiwana.movies.dagger

import com.utachiwana.movies.BuildConfig
import com.utachiwana.movies.data.network.MovieApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RetrofitModule {

    @Provides
    @Singleton
    fun provideOkHttpClient() : OkHttpClient = OkHttpClient().newBuilder().addInterceptor { chain ->
        val url =
            chain.request().url().newBuilder().addQueryParameter("api-key", BuildConfig.API_KEY).build()
        chain.proceed(chain.request().newBuilder().url(url).build())
    }.build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(MovieApi.URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideRetrofitApi(retrofit: Retrofit): MovieApi = retrofit.create(MovieApi::class.java)


}