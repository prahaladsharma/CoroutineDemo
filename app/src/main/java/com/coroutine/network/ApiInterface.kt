package com.coroutine.network

import com.coroutine.pojo.MovieReviews
import com.coroutine.util.SystemUtils.Companion.API_KEY
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("v2/reviews/all.json")
    suspend fun getCurrency(@Query("offset") offset: Int,
                            @Query("order") order: String,): Response<MovieReviews>



    companion object {
        private val interceptor = Interceptor { chain ->
            val url = chain.request().url().newBuilder().addQueryParameter("api-key", API_KEY).build()
            val request = chain.request()
                .newBuilder()
                .url(url)
                .build()
            chain.proceed(request)
        }

        var retrofitService: ApiInterface? = null
        fun getInstance() : ApiInterface {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://api.nytimes.com/svc/movies/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(OkHttpClient().newBuilder().addInterceptor(interceptor).build())
                    .build()
                retrofitService = retrofit.create(ApiInterface::class.java)
            }
            return retrofitService!!
        }
    }
}