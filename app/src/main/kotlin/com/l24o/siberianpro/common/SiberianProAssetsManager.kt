package com.l24o.siberianpro.common

import android.content.Context
import com.google.gson.Gson
import com.l24o.siberianpro.data.rest.models.Products
import rx.Observable
import java.nio.charset.Charset
import javax.inject.Inject

/**
 * @author Alexander Popov on 22/02/2017.
 */
class SiberianProAssetsManager @Inject constructor(private val context: Context) {

    fun getProducts(): Observable<Products> {
        var json: String? = null
        val inputStream = context.assets.open("data.json")
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        json = String(buffer, Charset.defaultCharset())
        return Observable.just(Gson().fromJson(json, Products::class.java))
    }

}