package com.l24o.siberianpro.common.mvp

import com.l24o.siberianpro.TemplateApplication

interface IView {
    fun application(): TemplateApplication
    fun showMessage(message: String)
    fun showMessage(messageResId: Int)
}


interface IPresenter<in V : IView> {
    fun takeView(view: V)
    fun onViewAttached()
    fun dropView()
    fun onViewDetached()
}
