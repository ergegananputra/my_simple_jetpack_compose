package com.adielboanerge.interntest.third_screen.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adielboanerge.interntest.third_screen.data.model.RegresContactResponse
import com.adielboanerge.interntest.third_screen.data.network.RegresContactApiService
import com.adielboanerge.interntest.third_screen.data.network.Retro
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ThirdScreenViewModel : ViewModel() {
    private val _state = MutableStateFlow(ThirdScreenState())
    val state : StateFlow<ThirdScreenState> = _state.asStateFlow()

    fun updateEndReached(endReached: Boolean) {
        _state.value = _state.value.copy(isLoadMore = endReached)
    }


    fun updateIsRefreshing(isRefreshing: Boolean) {
        _state.value = _state.value.copy(isRefreshing = isRefreshing)
    }

    private val regresContactAPI =
        Retro()
            .getRetroClientInstance()
            .create(RegresContactApiService::class.java)


    fun refreshContacts() {
        viewModelScope.launch(Dispatchers.IO) {
            updateIsRefreshing(true)
            getRegresContact(page = 1)
        }
    }

    fun loadMoreContacts() {
        viewModelScope.launch(Dispatchers.IO) {
            updateIsRefreshing(true)
            val currentPage = _state.value.regresContactList.size / 10 + 1
            getRegresContact(page = currentPage)

        }

    }

    private fun getRegresContact(
        page: Int = 1,
        perPage: Int = 10,
        isReseted: Boolean = false
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            regresContactAPI
                .getContacts(
                    page = page,
                    perPage = perPage,
                ).enqueue(object : Callback<RegresContactResponse> {
                    override fun onResponse(
                        call: Call<RegresContactResponse>,
                        response: Response<RegresContactResponse>,
                    ) {
                        Log.d("ThirdScreenViewModel", "Response: $response")
                        if (response.isSuccessful) {
                            val regresContactResponse = response.body()
                            if (regresContactResponse != null) {
                                Log.d("ThirdScreenViewModel", "Response: $regresContactResponse")
                                val data = regresContactResponse.data

                                if (data.isNullOrEmpty()) {
                                    updateIsRefreshing(false)
                                    return
                                }

                                _state.value = _state.value.copy(
                                    regresContactList =  if (isReseted) data else _state.value.regresContactList + data,
                                    isLoadMore = regresContactResponse.page == regresContactResponse.totalPages
                                )
                                updateIsRefreshing(false)
                            } else {
                                Log.e("ThirdScreenViewModel", "Response is null")
                            }
                        } else {
                            Log.e("ThirdScreenViewModel", "Error: ${response.errorBody()}")
                        }
                    }

                    override fun onFailure(call: Call<RegresContactResponse>, t: Throwable) {
                        Log.e("ThirdScreenViewModel", "Error: ${t.message}")
                    }

                })
        }
    }

}