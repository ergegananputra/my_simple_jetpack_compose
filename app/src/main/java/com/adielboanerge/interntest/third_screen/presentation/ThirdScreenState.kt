package com.adielboanerge.interntest.third_screen.presentation

import com.adielboanerge.interntest.third_screen.data.model.RegresContact


data class ThirdScreenState(
    var name: String = "",
    var isRefreshing: Boolean = false,
    var isLoadMore : Boolean = false,
    var regresContactList: List<RegresContact> = emptyList()
)
