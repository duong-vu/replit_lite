package com.dvu.replitlite.application

import com.dvu.replitlite.api.ApiModule
import com.dvu.replitlite.keyboard.KeyboardModule
import com.dvu.replitlite.main.MainModule
import com.dvu.replitlite.repository.RepositoryModule
import com.dvu.replitlite.retrofit.RetrofitModule

internal val appModules =
    listOf(
        ApiModule.module,
        KeyboardModule.module,
        MainModule.module,
        RepositoryModule.module,
        RetrofitModule.module
    )