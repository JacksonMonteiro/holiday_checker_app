package com.jacksonmonteiro.holidaychecker.data.repository

import com.jacksonmonteiro.holidaychecker.data.local.dao.HolidayDAO
import com.jacksonmonteiro.holidaychecker.data.local.entities.HolidayEntity
import com.jacksonmonteiro.holidaychecker.data.mappers.toDomain
import com.jacksonmonteiro.holidaychecker.data.remote.service.HolidayAPIService
import com.jacksonmonteiro.holidaychecker.domain.model.Holiday
import com.jacksonmonteiro.holidaychecker.domain.repository.HolidayRepository
import io.ktor.client.plugins.ResponseException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json

class HolidayRepositoryImpl(private val api: HolidayAPIService, private val dao: HolidayDAO) :
    HolidayRepository {
    override fun fetchHolidaysByYearAndCountry(
        year: Int,
        countryCode: String
    ): Flow<Result<List<Holiday>>> = flow {
        val cached = dao.getByYearAndContry(year, countryCode).first()
        if (cached.isNotEmpty()) {
            val json = cached.first().holidays
            val holidays = Json.decodeFromString<List<Holiday>>(json)
            emit(Result.success(holidays))
        } else {
            try {
                val remote = api.fetchHolidaysByYearAndCountry(year, countryCode)
                dao.upsert(
                    HolidayEntity(
                        year = year,
                        contryCode = countryCode,
                        holidays = Json.encodeToString(remote)
                    )
                )
                emit(Result.success(remote.map { it.toDomain() }))
            } catch (e: ResponseException) {
                emit(Result.failure(e))
            }
        }
    }

}