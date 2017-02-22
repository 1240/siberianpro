package com.l24o.siberianpro.data.rest.repositories

import android.location.Location
import com.l24o.siberianpro.data.rest.datasource.ConductorDataSource
import com.l24o.siberianpro.data.rest.models.AuthResponse
import okhttp3.ResponseBody
import rx.Observable
import javax.inject.Inject

class ConductorRepository @Inject constructor(private val conductorDataSource: ConductorDataSource) : Repository() {

    fun authenticate(username: String, password: String) : Observable<AuthResponse> {
        return conductorDataSource.authenticate(username, password).compose(applySchedulers<AuthResponse>())
    }

    fun createByTicket(ticketId: String): Observable<ResponseBody> {
        return conductorDataSource.createByTicket(ticketId).compose(applySchedulers<ResponseBody>())
    }

    fun route(routeId: String): Observable<ResponseBody> {
        return conductorDataSource.route(routeId).compose(applySchedulers<ResponseBody>())
    }

    fun driver(driverId: String): Observable<ResponseBody> {
        return conductorDataSource.driver(driverId).compose(applySchedulers<ResponseBody>())
    }

}