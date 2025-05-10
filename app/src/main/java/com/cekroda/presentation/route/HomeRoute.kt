package com.cekroda.presentation.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import com.cekroda.model.Inspection
import com.cekroda.presentation.home.HomeEvent
import com.cekroda.presentation.home.HomeViewModel
import com.cekroda.ui.screen.home.HomeScreen
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeRoute(
    onAddInspectionClick: () -> Unit,
    onInspectionClick: (Inspection) -> Unit,
    viewModel: HomeViewModel = koinViewModel<HomeViewModel>()
) {
    val state = viewModel.state.collectAsState().value

    // Trigger load once
    LaunchedEffect(Unit) {
        viewModel.onEvent(HomeEvent.LoadInspections)
    }

    HomeScreen(
        state = state,
        onEvent = viewModel::onEvent,
        onAddInspectionClick = onAddInspectionClick,
        onInspectionClick = onInspectionClick
    )
}