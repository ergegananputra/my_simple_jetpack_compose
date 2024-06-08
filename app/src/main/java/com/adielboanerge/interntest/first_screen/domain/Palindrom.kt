package com.adielboanerge.interntest.first_screen.domain

fun isPalindrome(input: String) : Boolean {
    val cleanInput = input.replace(" ", "").lowercase()
    val reversedInput = cleanInput.reversed()

    return cleanInput == reversedInput
}