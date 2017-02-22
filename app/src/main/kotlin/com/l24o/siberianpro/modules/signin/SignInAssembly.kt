package com.l24o.siberianpro.modules.signin

import com.l24o.siberianpro.di.scopes.ActivityScope
import com.l24o.stels.di.AppComponent
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides

@ActivityScope
@Component(modules = arrayOf(SignInModule::class), dependencies = arrayOf(AppComponent::class))
interface SignInComponent {
    fun inject(signInActivity: SignInActivity)
}

@Module(includes = arrayOf(SignInModule.Declarations::class))
class SignInModule(val signInView: ISignInView) {

    @Provides
    fun provideLoginView(): ISignInView {
        return signInView
    }

    @Module
    interface Declarations {

        @Binds
        @ActivityScope
        fun bindLoginPresenter(signInPresenter: SignInPresenter): ISignInPresenter
    }
}

