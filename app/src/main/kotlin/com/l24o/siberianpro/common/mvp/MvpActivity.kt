package com.l24o.siberianpro.common.mvp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.l24o.siberianpro.TemplateApplication
import com.l24o.siberianpro.di.AppComponent
import com.l24o.siberianpro.common.mvp.IView
import org.jetbrains.anko.toast

abstract class MvpActivity: AppCompatActivity(), IView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        resolveDependencies(application().appComponent)
    }

    override fun onDestroy() {
        beforeDestroy()
        super.onDestroy()
    }

    abstract fun resolveDependencies(appComponent: AppComponent)

    abstract fun beforeDestroy()

    override fun application(): TemplateApplication {
        return application as TemplateApplication
    }

    override fun showMessage(message: String) {
        toast(message)
    }

    override fun showMessage(messageResId: Int) {
        toast(messageResId)
    }

}
