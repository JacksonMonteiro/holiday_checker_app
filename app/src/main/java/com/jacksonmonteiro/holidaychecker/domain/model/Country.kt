package com.jacksonmonteiro.holidaychecker.domain.model

import androidx.annotation.DrawableRes

data class Country(
    val name: String,
    val countryCode: String,
    @DrawableRes val icon: Int,
) {
    override fun toString(): String {
        return """
            Name: $name
            Country Code: $countryCode
        """.trimIndent()
    }
}
