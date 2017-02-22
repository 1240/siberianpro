package com.l24o.siberianpro.modules.signin

import com.l24o.siberianpro.data.rest.AuthProvider
import com.l24o.siberianpro.data.rest.repositories.AuthRepository
import com.l24o.siberianpro.data.rest.repositories.ConductorRepository
import com.l24o.stels.common.mvp.RxPresenter
import com.l24o.stels.extensions.parsedMessage
import rx.lang.kotlin.plusAssign
import javax.inject.Inject


class SignInPresenter @Inject constructor(view: ISignInView) : RxPresenter<ISignInView>(view), ISignInPresenter {

    @Inject lateinit var authProvider: AuthProvider
    @Inject lateinit var conductorRepo: ConductorRepository

    override fun onSignInClick(login: String, password: String) {
        authenticate(login, password)
    }

    private fun authenticate(login: String, password: String) {
        view?.setLoadingVisible(true)
        subscriptions += conductorRepo.authenticate(login, password)
                .subscribe(
                        { authResponse ->
                            authProvider.authToken = authResponse.authToken
                            view?.setLoadingVisible(false)
                        },
                        { error ->
                            view?.showMessage(error.parsedMessage())
                            view?.setLoadingVisible(false)
                        }
                )
    }
}