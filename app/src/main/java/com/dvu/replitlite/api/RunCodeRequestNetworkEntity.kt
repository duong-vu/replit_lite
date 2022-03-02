package com.dvu.replitlite.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RunCodeRequestNetworkEntity(
    @SerializedName("command")
    @Expose
    val command: String,

    @SerializedName("language")
    @Expose
    val language: String = "python"
)