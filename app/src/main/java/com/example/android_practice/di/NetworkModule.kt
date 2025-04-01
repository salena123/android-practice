package com.example.android_practice.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.android_practice.listWithDetails.data.api.DogApiService
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    factory { provideRetrofit(get()) }
    single<DogApiService> { provideNetworkApi(get()) }
}

fun provideRetrofit(context: Context): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://api.thedogapi.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(OkHttpClient.Builder().apply {
            addInterceptor { chain ->
                    val originalRequest = chain.request()
                    val url: HttpUrl = originalRequest.url.newBuilder()
                        .addQueryParameter("apikey", "live_DDxXvhUwOs3BYk8uDJXaTZqTCHCXe35GmmOOmrHRXNFHX8OcGbyhPSZKOGAVAp8v")
                        .build()
                    val newRequest = originalRequest.newBuilder()
                        .url(url)
                        .build()
                    chain.proceed(newRequest)

            }
            addInterceptor(ChuckerInterceptor(context))
        }.build())
        .build()
}

fun provideNetworkApi(retrofit: Retrofit): DogApiService =
    retrofit.create(DogApiService::class.java)