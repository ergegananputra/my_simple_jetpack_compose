package com.adielboanerge.interntest.first_screen.presentation

import kotlinx.serialization.Serializable


data class FirstScreenState(
    var name: String = "",
    var inputPalindrome: String = "",
    var isPalindrome: Boolean = false
)
