package com.jacksonmonteiro.holidaychecker.domain.useCases

import com.jacksonmonteiro.holidaychecker.domain.model.Holiday
import com.jacksonmonteiro.holidaychecker.domain.repository.HolidayRepository
import kotlinx.coroutines.flow.Flow

class FetchHolidaysUseCase(private val repository: HolidayRepository) {
    operator fun invoke(year: Int, countryCode: String): Flow<Result<List<Holiday>>> =
        repository.fetchHolidaysByYearAndCountry(year, countryCode)
}