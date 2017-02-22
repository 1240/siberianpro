package com.l24o.siberianpro.modules.signin

import com.l24o.stels.common.mvp.IPresenter
import com.l24o.stels.common.mvp.IView


interface ISignInView : IView {
    fun setLoadingVisible(isVisible: Boolean)
}

interface ISignInPresenter : IPresenter<ISignInView> {
    fun onSignInClick(login: String, password: String)
}

