package com.jacksonmonteiro.holidaychecker.di

import com.jacksonmonteiro.holidaychecker.data.remote.HttpClientFactory
import com.jacksonmonteiro.holidaychecker.data.remote.repository.HolidayRepository
import com.jacksonmonteiro.holidaychecker.data.remote.repository.HolidayRepositoryImpl
import com.jacksonmonteiro.holidaychecker.data.remote.service.HolidayAPIService
import org.koin.dsl.module

val dataModule = module {
    single { HttpClientFactory.create() }

    single { HolidayAPIService(get()) }

    single<HolidayRepository> { HolidayRepositoryImpl(get()) }
}