package com.adielboanerge.interntest.first_screen.presentation

import androidx.lifecycle.ViewModel
import com.adielboanerge.interntest.first_screen.domain.isPalindrome
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class FirstScreenViewModel : ViewModel() {
    private val _state = MutableStateFlow(FirstScreenState())

    val state : StateFlow<FirstScreenState> = _state.asStateFlow()

    fun updateName(name: String) {
        _state.value = _state.value.copy(name = name)
    }

    fun updateInputPalindrom(inputPalindrom: String) {
        _state.value = _state.value.copy(inputPalindrome = inputPalindrom)
    }

    fun checkPalindrome() {
        val isPalindrome = isPalindrome(_state.value.inputPalindrome)
        _state.value = _state.value.copy(isPalindrome = isPalindrome)
    }

}