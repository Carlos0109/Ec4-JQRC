package com.carlos.ec03_qrjc.fragments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlos.ec03_qrjc.data.retrofit.repository.DigimonRepository
import com.carlos.ec03_qrjc.data.retrofit.response.ApiResponse
import com.carlos.ec03_qrjc.model.Digimont
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CouponListViewModel: ViewModel() {

    private val repository = DigimonRepository()
    val digimonList: MutableLiveData<List<Digimont>> = MutableLiveData()

    fun getDigimonsFromService() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getDigimons()
            when (response) {
                is ApiResponse.Error -> {
                    // Manejar el caso de respuesta no exitosa

                }
                is ApiResponse.Success -> {
                    digimonList.postValue(response.data.toList())
                }
            }
        }
    }
}