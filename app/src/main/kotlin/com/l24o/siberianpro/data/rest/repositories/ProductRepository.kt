package com.l24o.siberianpro.data.rest.repositories

import com.l24o.siberianpro.data.rest.datasource.ProductDataSource
import com.l24o.siberianpro.data.rest.models.Products
import rx.Observable
import javax.inject.Inject


class ProductRepository @Inject constructor(private val productDataSource: ProductDataSource) : Repository() {

    fun getProducts(): Observable<Products> {
        return productDataSource.getGroups().compose(applySchedulers<Products>())
    }
}