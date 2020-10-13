package com.vmo.ecom.di

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.vmo.ecom.BuildConfig
import com.vmo.ecom.constant.Constant
import com.vmo.ecom.data.repository.ShopRepositoryImpl
import com.vmo.ecom.data.service.ShopService
import com.vmo.ecom.domain.repository.ShopRepository
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val dataModule = module {
    single {
        val builder = OkHttpClient.Builder()
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .connectTimeout(20, TimeUnit.SECONDS)
        // add some debugger or interceptor if needed
        if (BuildConfig.DEBUG) {
            builder.addNetworkInterceptor(StethoInterceptor())
        }
        return@single builder.build()
    }

    single {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constant.BASE_URL)
            .client(get())
            .build()
    }

    single<ShopService> { createWebService(get()) }
    factory<ShopRepository> {
        ShopRepositoryImpl(get(), get())
    }
}

inline fun <reified T> createWebService(retrofit: Retrofit): T {
    return retrofit.create(T::class.java)
}