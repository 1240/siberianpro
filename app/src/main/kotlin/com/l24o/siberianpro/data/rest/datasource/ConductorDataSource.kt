package com.l24o.siberianpro.data.rest.datasource

import com.l24o.siberianpro.data.rest.models.AuthResponse
import okhttp3.ResponseBody
import retrofit2.http.*
import rx.Observable

interface ConductorDataSource {

    @GET("/conductor/v1/auth/authenticate")
    fun authenticate(@Query("username") username: String, @Query("password") password: String): Observable<AuthResponse>


    @POST("/conductor/v1/createByTicket")
    @FormUrlEncoded
    fun createByTicket(@Field("ticket_id") ticketId: String): Observable<ResponseBody>

    @POST("/conductor/v1/driver")
    @FormUrlEncoded
    fun driver(@Field("driver_id") driverId: String): Observable<ResponseBody>

    @POST("/conductor/v1/route")
    @FormUrlEncoded
    fun route(@Field("route_id") routeId: String): Observable<ResponseBody>

}