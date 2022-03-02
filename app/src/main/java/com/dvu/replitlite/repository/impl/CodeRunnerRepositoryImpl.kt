package com.dvu.replitlite.repository.impl

import com.dvu.replitlite.api.CodeRunnerApi
import com.dvu.replitlite.api.RunCodeRequestNetworkEntity
import com.dvu.replitlite.repository.intf.CodeRunnerRepository
import com.dvu.replitlite.repository.model.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class CodeRunnerRepositoryImpl(private val codeRunnerApi: CodeRunnerApi) : CodeRunnerRepository {
    override suspend fun runCode(code: String): Flow<Resource<String>> = flow {
        emit(Resource.Loading)

        // Simulate slow network response for testing
        delay(1000)

        try {
            val response = codeRunnerApi.runCode(RunCodeRequestNetworkEntity(command = code))
            emit(Resource.Success(response.result))
        } catch (e: Exception) {
            emit(Resource.Error(e))
        }
    }
}