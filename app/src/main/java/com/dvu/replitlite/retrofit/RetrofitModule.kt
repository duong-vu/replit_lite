package com.dvu.replitlite.retrofit

import com.google.gson.GsonBuilder
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private const val BASE_URL = "https://eval-backend.DuongVu12.repl.co/"

object RetrofitModule {
    val module = module {
        single { GsonBuilder().excludeFieldsWithoutExposeAnnotation().create() }

        single {
            Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(get())).build()
        }
    }
}