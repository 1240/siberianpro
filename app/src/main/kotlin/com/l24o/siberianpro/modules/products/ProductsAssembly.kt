package com.l24o.siberianpro.modules.products

import com.l24o.siberianpro.di.AppComponent
import com.l24o.siberianpro.di.scopes.ActivityScope
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides

@ActivityScope
@Component(modules = arrayOf(ProductsModule::class), dependencies = arrayOf(AppComponent::class))
interface ProductsComponent {
    fun inject(productsActivity: ProductsActivity)
}

@Module(includes = arrayOf(ProductsModule.Declarations::class))
class ProductsModule(val productsView: IProductsView) {

    @Provides
    fun provideView(): IProductsView {
        return productsView
    }

    @Module
    interface Declarations {

        @Binds
        @ActivityScope
        fun bindPresenter(productsPresenter: ProductsPresenter): IProductsPresenter
    }
}

