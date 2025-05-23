package com.cekroda.presentation.home

import com.cekroda.model.Inspection

sealed class HomeEvent {
    object LoadInspections : HomeEvent()
    object SaveInspection : HomeEvent()
}