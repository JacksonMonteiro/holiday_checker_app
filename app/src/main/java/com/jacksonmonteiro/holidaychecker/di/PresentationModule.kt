package com.jacksonmonteiro.holidaychecker.di


import com.jacksonmonteiro.holidaychecker.presentation.holidays.HolidaysViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { HolidaysViewModel(get()) }
}