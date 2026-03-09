package com.jacksonmonteiro.holidaychecker.data.remote.repository

import com.jacksonmonteiro.holidaychecker.data.mappers.toDomain
import com.jacksonmonteiro.holidaychecker.data.remote.service.HolidayAPIService
import com.jacksonmonteiro.holidaychecker.domain.model.Holiday
import io.ktor.client.plugins.ResponseException

class HolidayRepositoryImpl(private val api: HolidayAPIService) : HolidayRepository {
    override suspend fun fetchHolidaysByYearAndCountry(
        year: Int,
        countryCode: String
    ): Result<List<Holiday>> = runCatching {
        try {
            val remote = api.fetchHolidaysByYearAndCountry(year, countryCode).map { it.toDomain() }
            remote
        } catch (e: ResponseException) {
            emptyList()
        }
    }

}