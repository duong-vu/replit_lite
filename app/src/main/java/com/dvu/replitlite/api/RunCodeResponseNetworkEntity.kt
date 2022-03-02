package com.dvu.replitlite.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RunCodeResponseNetworkEntity(
    @SerializedName("result")
    @Expose
    val result: String
)