package io.marelso.shineyard.data.network

import io.marelso.shineyard.data.Constants
import okhttp3.OkHttpClient
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    factory(named(Constants.YARD_API.NAME)) {
        Retrofit.Builder().client(OkHttpClient.Builder().apply {
            addInterceptor { chain ->
                chain.proceed(
                    chain.request().newBuilder().apply {
//                        addHeader("Authorization", Session.token.orEmpty())
                    }.build()
                )
            }
        }.build()).baseUrl(Constants.YARD_API.URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }
}