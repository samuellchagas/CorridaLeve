package com.example.corridaleve

import android.content.Context
import android.content.SharedPreferences
import com.example.corridaleve.data.database.DatabaseApp
import com.example.corridaleve.repository.HistoricRepository
import com.example.corridaleve.repository.LoginRepository
import com.example.corridaleve.repository.ScreenRunRepository
import com.example.corridaleve.viewmodel.HistoricViewModel
import com.example.corridaleve.viewmodel.LoginViewModel
import com.example.corridaleve.viewmodel.RegisterViewModel
import com.example.corridaleve.viewmodel.ScreenRunViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val injectDependence = module {
    viewModel { LoginViewModel(get()) } // cria view model, e adiciona depenencia
    viewModel { RegisterViewModel() }
    viewModel{ ScreenRunViewModel(get())}
    viewModel { HistoricViewModel(get()) }
    factory { LoginRepository(get()) }  // cria o repository e adicona dependencias do repository
    factory { ScreenRunRepository(get()) }
    factory { HistoricRepository(get()) }
    single { SharedPreferenceCorrida(get()) }
    single { androidContext().getSharedPreferences("saveData", Context.MODE_PRIVATE) } // onde ficam as dependencia
    single { DatabaseApp.getInstance(androidContext()).historicDAO() }
}
