package com.l24o.siberianpro.di.modules

import com.google.gson.Gson
import com.l24o.siberianpro.Constants
import com.l24o.siberianpro.data.rest.AuthProvider
import com.l24o.siberianpro.data.rest.TemplateInterceptor
import com.l24o.siberianpro.data.rest.datasource.AuthDataSource
import com.l24o.siberianpro.data.rest.datasource.ConductorDataSource
import com.l24o.siberianpro.data.rest.datasource.RegistrarDataSource
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideDefaultHTTPClient(templateInterceptor: TemplateInterceptor): OkHttpClient {
        val client = OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
                .addInterceptor(templateInterceptor)
                .build()

        return client
    }

    @Provides
    @Singleton
    fun provideTemplateInterceptor(authProvider: AuthProvider): TemplateInterceptor {
        return TemplateInterceptor(authProvider)
    }

    @Provides
    @Singleton
    fun provideAuthProvider(): AuthProvider {
        return AuthProvider()
    }

    @Provides
    @Singleton
    @Named("default")
    fun provideDefaultRetrofitAdapter(client: OkHttpClient, gson: Gson): Retrofit {
        val adapter = Retrofit.Builder()
                .baseUrl(Constants.API_MAIN_ENDPOINT_URL)
                .client(client)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

        return adapter
    }

    @Provides
    @Singleton
    @Named("registrar")
    fun provideRegistrarRetrofitAdapter(client: OkHttpClient, gson: Gson): Retrofit {
        val adapter = Retrofit.Builder()
                .baseUrl(Constants.API_REGISTRAR_ENDPOINT_URL)
                .client(client)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

        return adapter
    }
    @Provides
    @Singleton
    @Named("conductor")
    fun provideConductorRetrofitAdapter(client: OkHttpClient, gson: Gson): Retrofit {
        val adapter = Retrofit.Builder()
                .baseUrl(Constants.API_REGISTRAR_ENDPOINT_URL)
                .client(client)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

        return adapter
    }

    @Provides
    @Singleton
    fun provideAuthDataSource(@Named("default") retrofit: Retrofit): AuthDataSource {
        return retrofit.create(AuthDataSource::class.java)
    }

    @Provides
    @Singleton
    fun provideRegistrarDataSource(@Named("registrar") retrofit: Retrofit): RegistrarDataSource {
        return retrofit.create(RegistrarDataSource::class.java)
    }

    @Provides
    @Singleton
    fun provideConductorDataSource(@Named("conductor") retrofit: Retrofit): ConductorDataSource {
        return retrofit.create(ConductorDataSource::class.java)
    }
}