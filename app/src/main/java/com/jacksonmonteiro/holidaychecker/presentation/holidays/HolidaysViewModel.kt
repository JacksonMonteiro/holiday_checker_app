package com.jacksonmonteiro.holidaychecker.presentation.holidays

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jacksonmonteiro.holidaychecker.domain.useCases.FetchHolidaysUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HolidaysViewModel(private val fetchHolidays: FetchHolidaysUseCase) : ViewModel() {
    private val _state = MutableStateFlow(HolidaysUIState())
    val state: StateFlow<HolidaysUIState> get() = _state

    fun onEvent(event: HolidaysEvent) {
        when (event) {
            is HolidaysEvent.FetchHolidays -> fetch(event.year, event.countryCode)
        }
    }

    private fun fetch(year: Int, countryCode: String) = viewModelScope.launch {
        _state.update { it.copy(isLoading = true, errorMessage = null) }
        fetchHolidays(year, countryCode).fold(
            onSuccess = { holidays ->
                _state.update { it.copy(holidays = holidays, isLoading = false) }
            },
            onFailure = { error ->
                _state.update { it.copy(errorMessage = error.message, isLoading = false) }
            }
        )
    }
}