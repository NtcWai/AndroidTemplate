package com.vmo.ecom.presentation

import android.app.Application
import com.facebook.stetho.Stetho
import com.vmo.ecom.presentation.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class EcomApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this);
        startKoin {
            androidContext(this@EcomApplication)
            modules(appModules)
        }
    }
}