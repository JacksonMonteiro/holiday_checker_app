package com.jacksonmonteiro.holidaychecker.data.remote.service

import com.jacksonmonteiro.holidaychecker.data.remote.ApiConfig
import com.jacksonmonteiro.holidaychecker.data.remote.dto.HolidayDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class HolidayAPIService(private val client: HttpClient) {
    suspend fun fetchHolidaysByYearAndCountry(year: Int, countryCode: String): List<HolidayDto> =
        client.get(
            ApiConfig.BASE_URL + ApiConfig.Endpoints.holidaysByYearAndCountry(
                year,
                countryCode
            )
        ).body()
}