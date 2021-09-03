package com.example.corridaleve

import android.app.Application
import com.example.corridaleve.viewmodel.LoginViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

class MyApp:Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {

            androidContext(this@MyApp)
            modules(injectDependence)
        }

    }

}
