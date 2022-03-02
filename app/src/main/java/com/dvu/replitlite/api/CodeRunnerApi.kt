package com.dvu.replitlite.api

import retrofit2.http.Body
import retrofit2.http.PUT

interface CodeRunnerApi {

    @PUT("exec")
    suspend fun runCode(@Body request: RunCodeRequestNetworkEntity): RunCodeResponseNetworkEntity
}