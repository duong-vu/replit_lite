package com.dvu.replitlite.keyboard

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object KeyboardModule {

    val module = module {
        viewModel { KeyboardVisibilityViewModel() }
    }
}