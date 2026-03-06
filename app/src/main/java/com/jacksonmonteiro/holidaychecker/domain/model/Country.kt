package com.jacksonmonteiro.holidaychecker.domain.model

import androidx.annotation.DrawableRes

data class Country(
    val name: String,
    val countryCode: String,
    @DrawableRes val icon: Int,
)
