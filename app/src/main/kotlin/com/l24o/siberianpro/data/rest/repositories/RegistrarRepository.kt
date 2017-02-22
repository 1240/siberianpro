package com.l24o.siberianpro.data.rest.repositories

import com.l24o.siberianpro.data.rest.datasource.RegistrarDataSource
import okhttp3.ResponseBody
import rx.Observable
import javax.inject.Inject

class RegistrarRepository @Inject constructor(private val registrarDataSource: RegistrarDataSource) : Repository() {
    fun validateUser(userId: String): Observable<ResponseBody> {
        return registrarDataSource.validateUser(userId).compose(applySchedulers<ResponseBody>())
    }

    fun activateUserAccount(userId: String): Observable<ResponseBody> {
        return registrarDataSource.activateUserAccount(userId).compose(applySchedulers<ResponseBody>())
    }
}