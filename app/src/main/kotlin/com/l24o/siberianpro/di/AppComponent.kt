package com.l24o.siberianpro.di

import android.content.Context
import com.google.gson.Gson
import com.l24o.siberianpro.data.rest.datasource.ProductDataSource
import com.l24o.siberianpro.di.modules.AppModule
import com.l24o.siberianpro.di.modules.NetworkModule
import dagger.Component
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class, NetworkModule::class))
interface AppComponent {
    fun proivdeGson(): Gson
    fun provideHttpClient(): OkHttpClient
    fun provideProductDataSource(): ProductDataSource
    fun provideContext(): Context
}