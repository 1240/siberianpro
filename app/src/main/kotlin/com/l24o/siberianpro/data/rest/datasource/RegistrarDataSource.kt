package com.l24o.siberianpro.data.rest.datasource

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

interface RegistrarDataSource {

    @GET("user/validate")
    fun validateUser(@Query("userId") userId: String): Observable<ResponseBody>

    @GET("user/activate")
    fun activateUserAccount(@Query("userId") userId: String): Observable<ResponseBody>

    @GET("user/agreement")
    fun downloadUserAgreement(@Query("userId") userId: String): Observable<ResponseBody>
}