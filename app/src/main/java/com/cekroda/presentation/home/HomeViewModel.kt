package com.cekroda.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cekroda.data.repository.InspectionRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: InspectionRepository
) : ViewModel() {
    private val _state = MutableStateFlow(HomeState()) // MutableStateFlow untuk internal
    val state: StateFlow<HomeState> = _state // StateFlow untuk expose ke UI

    init {
        onEvent(HomeEvent.LoadInspections) // load saat init
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.LoadInspections -> {
                loadInspections()
            }
        }
    }

    private fun loadInspections() {
        viewModelScope.launch {
            repository.getAll()
                .onStart {
                    _state.update { it.copy(isLoading = true, error = null) }
                }
                .catch { e ->
                    _state.update { it.copy(isLoading = false, error = e.message ?: "Unknown error") }
                }
                .collect { inspections ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            inspections = inspections,
                            error = null
                        )
                    }
                }
        }
    }
}