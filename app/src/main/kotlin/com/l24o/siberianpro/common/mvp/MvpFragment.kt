package com.l24o.siberianpro.common.mvp

import android.app.Fragment
import android.os.Bundle
import com.l24o.siberianpro.TemplateApplication
import com.l24o.siberianpro.di.AppComponent
import org.jetbrains.anko.toast


abstract class MvpFragment : Fragment(), IView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependencies(application().appComponent)
    }

    override fun onDestroy() {
        beforeDestroy()
        super.onDestroy()
    }

    abstract fun injectDependencies(appComponent: AppComponent)

    abstract fun beforeDestroy()

    override fun application(): TemplateApplication {
        return activity.application as TemplateApplication
    }

    override fun showMessage(message: String) {
        toast(message)
    }

    override fun showMessage(messageResId: Int) {
        toast(messageResId)
    }

}