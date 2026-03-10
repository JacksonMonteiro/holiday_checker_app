package com.jacksonmonteiro.holidaychecker.domain.useCases

import com.jacksonmonteiro.holidaychecker.domain.model.Holiday
import com.jacksonmonteiro.holidaychecker.domain.repository.HolidayRepository

class FetchHolidaysUseCase(private val repository: HolidayRepository) {
    suspend operator fun invoke(year: Int, countryCode: String): Result<List<Holiday>> =
        repository.fetchHolidaysByYearAndCountry(year, countryCode)
}