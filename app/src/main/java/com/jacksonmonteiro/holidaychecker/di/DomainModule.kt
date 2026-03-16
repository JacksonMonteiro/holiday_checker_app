package com.jacksonmonteiro.holidaychecker.di

import com.jacksonmonteiro.holidaychecker.domain.useCases.FetchHolidaysUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { FetchHolidaysUseCase(get()) }
}