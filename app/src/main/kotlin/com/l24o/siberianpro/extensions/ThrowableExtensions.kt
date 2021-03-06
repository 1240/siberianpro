package com.l24o.siberianpro.extensions

import org.json.JSONObject
import retrofit2.adapter.rxjava.HttpException

private const val UNKNOWN_ERROR_MESSAGE = "Unknown error"

fun Throwable.parsedMessage(): String {
    if (this is HttpException) {
        val responseString = response().errorBody().string()
        return JSONObject(responseString).optString("message", UNKNOWN_ERROR_MESSAGE)
    }

    return message ?: UNKNOWN_ERROR_MESSAGE
}