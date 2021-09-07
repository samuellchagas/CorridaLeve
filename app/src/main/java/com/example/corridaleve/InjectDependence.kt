package com.example.corridaleve

import android.content.Context
import android.content.SharedPreferences
import com.example.corridaleve.repository.LoginRepository
import com.example.corridaleve.viewmodel.LoginViewModel
import com.example.corridaleve.viewmodel.RegisterViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val injectDependence = module {
    viewModel { LoginViewModel(get()) } // cria view model, e adiciona depenencia
    viewModel { RegisterViewModel() }
    factory { LoginRepository(get()) }  // cria o repository e adicona dependencias do repository
    single { SharedPreferenceCorrida(get()) }
    single { androidContext().getSharedPreferences("saveData", Context.MODE_PRIVATE) } // onde ficam as dependencia

}
