package com.jacksonmonteiro.holidaychecker.domain.repository

import com.jacksonmonteiro.holidaychecker.domain.model.Holiday
import kotlinx.coroutines.flow.Flow

interface HolidayRepository {
    fun fetchHolidaysByYearAndCountry(
        year: Int,
        countryCode: String
    ): Flow<Result<List<Holiday>>>
}