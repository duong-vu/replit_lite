package com.dvu.replitlite.repository.intf

import com.dvu.replitlite.repository.model.Resource
import kotlinx.coroutines.flow.Flow

interface CodeRunnerRepository {

    suspend fun runCode(code: String): Flow<Resource<String>>
}