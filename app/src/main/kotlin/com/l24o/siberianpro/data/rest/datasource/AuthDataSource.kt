package com.l24o.siberianpro.data.rest.datasource

import com.l24o.siberianpro.data.rest.models.AuthResponse
import retrofit2.http.GET
import retrofit2.http.QueryMap
import rx.Observable


interface AuthDataSource {

    @GET("auth/authenticate")
    fun authenticate(@QueryMap params: Map<String, String>): Observable<AuthResponse>
}