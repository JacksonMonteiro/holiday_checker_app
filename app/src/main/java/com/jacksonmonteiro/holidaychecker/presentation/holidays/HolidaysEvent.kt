package com.jacksonmonteiro.holidaychecker.presentation.holidays

sealed class HolidaysEvent {
    data class FetchHolidays(val year: Int, val countryCode: String) : HolidaysEvent()
}