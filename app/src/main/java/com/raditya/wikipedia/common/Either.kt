package com.raditya.wikipedia.common

sealed class Either<out T : Any> {

    data class Success<out T : Any>(val data: T) : Either<T>()
    data class Error(val exception: Throwable) : Either<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
        }
    }
}