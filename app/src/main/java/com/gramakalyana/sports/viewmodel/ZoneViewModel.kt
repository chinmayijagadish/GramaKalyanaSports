package com.gramakalyana.sports.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ZoneViewModel : ViewModel() {

    private val _selectedZone =
        MutableStateFlow("")

    val selectedZone: StateFlow<String>
        get() = _selectedZone

    fun updateZone(zone: String) {

        _selectedZone.value = zone
    }
}