package com.jacksonmonteiro.holidaychecker.data.repository

import com.jacksonmonteiro.holidaychecker.data.local.dao.HolidayDAO
import com.jacksonmonteiro.holidaychecker.data.mappers.toDomain
import com.jacksonmonteiro.holidaychecker.data.remote.service.HolidayAPIService
import com.jacksonmonteiro.holidaychecker.domain.model.Holiday
import com.jacksonmonteiro.holidaychecker.domain.repository.HolidayRepository
import io.ktor.client.plugins.ResponseException

class HolidayRepositoryImpl(private val api: HolidayAPIService, private val dao: HolidayDAO) :
    HolidayRepository {
    override suspend fun fetchHolidaysByYearAndCountry(
        year: Int,
        countryCode: String
    ): Result<List<Holiday>> = runCatching {
        //TODO: IMPLEMENT A CACHE VERIFICATION, IF EXISTS, EXTRACT HOLIDAYS AND SEND TO USER
        try {
            val remote = api.fetchHolidaysByYearAndCountry(year, countryCode).map { it.toDomain() }
            remote
        } catch (e: ResponseException) {
            emptyList()
        }
    }

}