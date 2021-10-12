package com.coroutine.network

import com.coroutine.BuildConfig
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitService {

    const val baseServer = "https://api.nytimes.com/svc/movies/"

    //creating a Network Interceptor to add api_key in all the request as authInterceptor
    private val interceptor = Interceptor { chain ->
        val url = chain.request().url().newBuilder().addQueryParameter("api-key", "n8Ajb1UX8fmeg3JnhcZs5Dd2bsEuuHPj").build()
        val request = chain.request()
            .newBuilder()
            .url(url)
            .build()
        chain.proceed(request)
    }


    val retrofitClient: Retrofit.Builder by lazy {

        val levelType: Level
        if (BuildConfig.BUILD_TYPE.contentEquals("debug"))
            levelType = Level.BODY else levelType = Level.NONE

        val logging = HttpLoggingInterceptor()
        logging.setLevel(levelType)

        val okhttpClient = OkHttpClient.Builder()
        okhttpClient.addInterceptor(logging)
        okhttpClient.addInterceptor(interceptor)

        Retrofit.Builder()
            .baseUrl(baseServer)
            .client(okhttpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
    }

    val apiInterface: ApiInterface by lazy {
        retrofitClient
            .build()
            .create(ApiInterface::class.java)
    }
}