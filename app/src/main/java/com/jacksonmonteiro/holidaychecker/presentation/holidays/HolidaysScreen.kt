package com.jacksonmonteiro.holidaychecker.presentation.holidays

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jacksonmonteiro.holidaychecker.R
import com.jacksonmonteiro.holidaychecker.domain.model.Country
import java.util.Calendar

@Composable
fun HolidaysScreen(modifier: Modifier = Modifier) {
    HolidaysScreenContent(modifier)
}

@Composable
fun HolidaysScreenContent(modifier: Modifier = Modifier) {
    val countries = listOf(Country("Brazil", "BR", R.drawable.flag_br))
    var expandedCountries by remember { mutableStateOf(false) }
    var selectedCountry by remember { mutableStateOf(countries[0]) }

    var expandedYears by remember { mutableStateOf(false) }
    var selectedYear by remember { mutableIntStateOf(Calendar.getInstance().get(Calendar.YEAR)) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFEFEFEF)),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                CountriesSpinner(
                    countries = countries,
                    expandedCountries = expandedCountries,
                    selectedCountry = selectedCountry,
                    onExpandChanged = { expandedCountries = !expandedCountries },
                    onDismissRequest = { expandedCountries = false },
                    onSelectCountry = { country ->
                        selectedCountry = country
                        expandedCountries = false
                        Log.d("YEAR", selectedCountry.toString())
                    })
                Spacer(modifier = Modifier.width(8.dp))
                YearsSpinner(
                    expandedYears,
                    selectedYear,
                    onExpandChanged = { expandedYears = !expandedYears },
                    onDismissRequest = { expandedYears = false },
                    onSelectYear = { year ->
                        selectedYear = year
                        expandedYears = false
                        Log.d("YEAR", "$selectedYear")
                    }
                )
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun CountriesSpinner(
    countries: List<Country>,
    expandedCountries: Boolean,
    selectedCountry: Country,
    onExpandChanged: (Boolean) -> Unit,
    onDismissRequest: () -> Unit,
    onSelectCountry: (Country) -> Unit,
) {
    ExposedDropdownMenuBox(
        expanded = expandedCountries,
        onExpandedChange = onExpandChanged,
        modifier = Modifier.fillMaxWidth(0.6f)
    ) {
        OutlinedTextField(
            value = selectedCountry.name,
            onValueChange = {},
            readOnly = true,
            leadingIcon = {
                Icon(
                    painter = painterResource(id = selectedCountry.icon),
                    contentDescription = selectedCountry.name,
                    tint = Color.Unspecified
                )
            },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedCountries)
            },
            modifier = Modifier.menuAnchor(
                MenuAnchorType.PrimaryNotEditable,
                true
            ),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedTextColor = Color.Black,
                focusedTextColor = Color.Black,
            )
        )
        ExposedDropdownMenu(
            expanded = expandedCountries,
            onDismissRequest = onDismissRequest
        ) {
            countries.forEach { country ->
                DropdownMenuItem(
                    text = { Text(country.name) },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(country.icon),
                            contentDescription = country.name,
                            tint = Color.Unspecified
                        )
                    },
                    onClick = {
                        onSelectCountry(country)
                    }
                )
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun YearsSpinner(
    expanded: Boolean,
    selected: Int,
    onExpandChanged: (Boolean) -> Unit,
    onDismissRequest: () -> Unit,
    onSelectYear: (Int) -> Unit
) {
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = onExpandChanged,
        modifier = Modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            value = selected.toString(),
            onValueChange = {},
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            modifier = Modifier.menuAnchor(MenuAnchorType.PrimaryNotEditable, true),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedTextColor = Color.Black,
                focusedTextColor = Color.Black
            )
        )
        ExposedDropdownMenu(expanded = expanded, onDismissRequest = onDismissRequest) {
            (1990..(Calendar.getInstance().get(Calendar.YEAR) + 10)).forEach { year ->
                DropdownMenuItem(
                    text = { Text(year.toString()) },
                    onClick = { onSelectYear(year) }
                )
            }
        }
    }
}