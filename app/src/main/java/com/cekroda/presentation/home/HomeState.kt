package com.cekroda.presentation.home

import com.cekroda.model.Inspection

data class HomeState(
    val isLoading: Boolean = false,
    val inspections: List<Inspection> = emptyList(),
    val error: String? = null
)