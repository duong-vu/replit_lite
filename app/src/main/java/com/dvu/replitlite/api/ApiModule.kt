package com.dvu.replitlite.api

import org.koin.dsl.module
import retrofit2.Retrofit

object ApiModule {

    val module = module {
        single { get<Retrofit>().create(CodeRunnerApi::class.java) }
    }
}