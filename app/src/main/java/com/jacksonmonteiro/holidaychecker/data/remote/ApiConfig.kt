package com.jacksonmonteiro.holidaychecker.data.remote

object ApiConfig {
    const val BASE_URL = "https://date.nager.at/api/v3"

    object Endpoints {
        const val HOLIDAYS = "/PublicHolidays"
        fun holidaysByYearAndCountry(year: Int, countryCode: String) =
            "/PublicHolidays/$year/$countryCode"
    }
}