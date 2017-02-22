package com.l24o.siberianpro.modules.signin

import android.os.Bundle
import android.view.View
import com.l24o.stels.di.AppComponent
import com.l24o.siberianpro.R
import com.l24o.siberianpro.common.inDebugMode
import com.l24o.siberianpro.common.mvp.MvpActivity
import kotlinx.android.synthetic.main.activity_signin.*
import org.jetbrains.anko.enabled
import org.jetbrains.anko.onClick
import javax.inject.Inject

class SignInActivity : MvpActivity(), ISignInView {

    @Inject lateinit var presenter: ISignInPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        initViews()
        presenter.onViewAttached()
    }

    override fun resolveDependencies(appComponent: AppComponent) {
        DaggerSignInComponent.builder()
                .appComponent(appComponent)
                .signInModule(SignInModule(this))
                .build()
                .inject(this)
    }

    override fun beforeDestroy() {
        presenter.dropView()
    }

    override fun setLoadingVisible(isVisible: Boolean) {
        progressBar.visibility = if (isVisible) View.VISIBLE else View.GONE
        emailEditText.enabled = !isVisible
        passwordEditText.enabled = !isVisible
        forgotPasswordTextView.enabled = !isVisible
        loginButton.enabled = !isVisible
    }

    private fun initViews() {
        inDebugMode {
            emailEditText.setText("template")
            passwordEditText.setText("template")
        }

        loginButton.onClick {
            presenter.onSignInClick(emailEditText.text.toString(), passwordEditText.text.toString())
        }
    }
}

