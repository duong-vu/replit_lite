package com.dvu.replitlite.main

import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object MainModule {

    val module = module {
        viewModel { CodeEditorViewModel(androidApplication(), get()) }
    }
}