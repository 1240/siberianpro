package com.l24o.siberianpro.data.rest.repositories

import com.l24o.siberianpro.data.rest.datasource.AuthDataSource
import com.l24o.siberianpro.data.rest.models.AuthResponse
import com.l24o.siberianpro.data.rest.models.DeviceType
import rx.Observable
import javax.inject.Inject


class AuthRepository @Inject constructor(private val authDataSource: AuthDataSource) : Repository() {

    fun authenticate(email: String,
                     password: String,
                     pushToken: String? = null): Observable<AuthResponse> {

        val params = mutableMapOf("email" to email,
                "password" to password,
                "deviceType" to DeviceType.ANDROID.apiValue)

        pushToken?.let {
            params.put("deviceToken", it)
        }

        return authDataSource.authenticate(params).compose(applySchedulers<AuthResponse>())
    }
}