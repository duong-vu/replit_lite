package com.dvu.replitlite.repository

import com.dvu.replitlite.repository.impl.CodeRunnerRepositoryImpl
import com.dvu.replitlite.repository.intf.CodeRunnerRepository
import org.koin.dsl.module

object RepositoryModule {

    val module = module {
        single<CodeRunnerRepository> { CodeRunnerRepositoryImpl(get()) }
    }
}