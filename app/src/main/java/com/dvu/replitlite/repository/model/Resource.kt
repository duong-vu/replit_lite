package com.dvu.replitlite.repository.model

import java.lang.Exception

sealed class Resource<out R> {

    object Loading : Resource<Nothing>()

    class Success<T>(val data: T) : Resource<T>()

    class Error(val exception: Exception) : Resource<Nothing>()
}