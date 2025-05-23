package com.cekroda.presentation.route

import androidx.compose.runtime.Composable
import com.cekroda.model.Inspection
import com.cekroda.presentation.home.HomeEvent
import com.cekroda.presentation.home.HomeViewModel
import com.cekroda.ui.screen.inspection.AddInspectionScreen
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun InspectionRoute(
    onAddInspectionOnBoardingClick: (Inspection) -> Unit,
    viewModel: HomeViewModel = koinViewModel<HomeViewModel>()
) {

    AddInspectionScreen(onSave = {
        viewModel.onEvent(HomeEvent.SaveInspection, it)
        onAddInspectionOnBoardingClick(it)
    })
}