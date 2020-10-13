package com.vmo.ecom.presentation.di

import com.vmo.ecom.di.dataModule
import com.vmo.ecom.presentation.ui.shop.list.ShopListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { ShopListViewModel(get()) }
}

val appModules = listOf(
    dataModule,
    presentationModule
)