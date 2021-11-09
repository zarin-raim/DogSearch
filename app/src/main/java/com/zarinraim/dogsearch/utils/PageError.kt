package com.zarinraim.dogsearch.utils

import retrofit2.HttpException
import java.io.IOException

@JvmInline
value class PageError(val msg: String)

fun toPageError(e: Throwable): PageError {
    return when (e) {
        is HttpException -> {
            PageError(
                msg = e.localizedMessage ?: "An unexpected error occurred."
            )
        }
        is IOException -> {
            PageError(msg = "Please, check your internet connection and try again.")
        }
        else -> PageError(msg = "An unexpected error occurred.")
    }
}
