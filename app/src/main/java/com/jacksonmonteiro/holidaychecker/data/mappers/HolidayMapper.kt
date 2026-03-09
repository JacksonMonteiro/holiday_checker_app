package com.jacksonmonteiro.holidaychecker.data.mappers

import com.jacksonmonteiro.holidaychecker.data.remote.dto.HolidayDto
import com.jacksonmonteiro.holidaychecker.domain.model.Holiday

fun HolidayDto.toDomain() = Holiday(
    date = this.date,
    localName = this.localName,
    name = this.name,
    countryCode = this.countryCode,
    fixed = this.fixed,
    global = this.global,
    counties = this.counties,
    launchYear = this.launchYear,
    types = this.types,
)