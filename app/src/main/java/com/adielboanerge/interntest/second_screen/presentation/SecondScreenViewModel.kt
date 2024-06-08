package com.adielboanerge.interntest.second_screen.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SecondScreenViewModel : ViewModel() {
    private val _state = MutableStateFlow(SecondScreenState())

    val state : StateFlow<SecondScreenState> = _state.asStateFlow()

    fun updateName(name: String) {
        _state.value = _state.value.copy(name = name)
    }

    fun updateSelectedUser(selectedUser: String) {
        _state.value = _state.value.copy(selectedUser = selectedUser)
    }
}