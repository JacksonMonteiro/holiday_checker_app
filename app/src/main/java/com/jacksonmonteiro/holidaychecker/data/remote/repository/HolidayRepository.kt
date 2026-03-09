package com.jacksonmonteiro.holidaychecker.data.remote.repository

import com.jacksonmonteiro.holidaychecker.domain.model.Holiday

interface HolidayRepository {
    suspend fun fetchHolidaysByYearAndCountry(year: Int, countryCode: String) : Result<List<Holiday>>
}