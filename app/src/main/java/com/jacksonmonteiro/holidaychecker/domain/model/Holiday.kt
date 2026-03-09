package com.jacksonmonteiro.holidaychecker.domain.model

data class Holiday(
    var date: String? = null,
    var localName: String? = null,
    var name: String? = null,
    var countryCode: String? = null,
    var fixed: Boolean = false,
    var global: Boolean = false,
    var counties: List<String>? = emptyList(),
    var launchYear: Int? = null,
    var types: List<String>? = emptyList(),
)
