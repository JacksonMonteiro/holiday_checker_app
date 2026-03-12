package com.jacksonmonteiro.holidaychecker.di

import com.jacksonmonteiro.holidaychecker.data.local.AppDatabase
import com.jacksonmonteiro.holidaychecker.data.remote.HttpClientFactory
import com.jacksonmonteiro.holidaychecker.data.remote.service.HolidayAPIService
import com.jacksonmonteiro.holidaychecker.data.repository.HolidayRepositoryImpl
import com.jacksonmonteiro.holidaychecker.domain.repository.HolidayRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {
    single { AppDatabase.getDatabase(androidContext()) }
    single { get<AppDatabase>().holidaysDAO() }

    single { HttpClientFactory.create() }

    single { HolidayAPIService(get()) }

    single<HolidayRepository> { HolidayRepositoryImpl(get(), get()) }
}