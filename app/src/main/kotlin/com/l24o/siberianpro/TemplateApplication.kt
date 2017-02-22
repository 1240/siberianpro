package com.l24o.siberianpro

import android.app.Application
import com.l24o.siberianpro.di.AppComponent
import com.l24o.siberianpro.di.DaggerAppComponent
import com.l24o.siberianpro.di.modules.AppModule
import uk.co.chrisjenx.calligraphy.CalligraphyConfig

class TemplateApplication : Application() {

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()

        CalligraphyConfig.initDefault(CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Roboto-Light.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        )
    }
}