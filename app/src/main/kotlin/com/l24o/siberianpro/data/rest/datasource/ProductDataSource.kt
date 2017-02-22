package com.l24o.siberianpro.data.rest.datasource

import com.l24o.siberianpro.data.rest.models.Products
import retrofit2.http.GET
import rx.Observable


interface ProductDataSource {

    @GET("..") //if we need to load from sever
    fun getGroups(): Observable<Products>
}