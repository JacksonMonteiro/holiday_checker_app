package com.jacksonmonteiro.holidaychecker.presentation.holidays

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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

@Composable
fun HolidaysScreen() {
    HolidaysScreenContent()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HolidaysScreenContent() {
    val countries = listOf(
        Country("Brazil", "BR", R.drawable.flag_br)
    )

    var text by remember { mutableStateOf("") }

    var expandedCountries by remember { mutableStateOf(false) }
    var selectedCountry by remember { mutableStateOf(countries[0]) }


    Box(
        modifier = Modifier
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
                ExposedDropdownMenuBox(
                    expanded = expandedCountries,
                    onExpandedChange = { expandedCountries = !expandedCountries },
                    modifier = Modifier.fillMaxWidth()) {
                    OutlinedTextField(
                        value = selectedCountry.name,
                        onValueChange = {},
                        readOnly = true,
                        leadingIcon = {
                            Icon(
                                painter = painterResource(selectedCountry.icon),
                                contentDescription = selectedCountry.name
                            )
                        },
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedCountries)
                        },
                    )
                    ExposedDropdownMenu(
                        expanded = expandedCountries,
                        onDismissRequest = { expandedCountries = false }
                    ) {
                        countries.forEach { country ->
                            DropdownMenuItem(
                                text = { Text(country.name) },
                                leadingIcon = {
                                    Icon(
                                        painter = painterResource(country.icon),
                                        contentDescription = country.name
                                    )
                                },
                                onClick = {
                                    selectedCountry = country
                                    expandedCountries = false
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewContent() {
    HolidaysScreenContent()
}