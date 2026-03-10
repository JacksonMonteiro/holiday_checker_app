package com.jacksonmonteiro.holidaychecker.presentation.holidays

import com.jacksonmonteiro.holidaychecker.domain.model.Holiday

data class HolidaysUIState(
    val holidays: List<Holiday> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
)