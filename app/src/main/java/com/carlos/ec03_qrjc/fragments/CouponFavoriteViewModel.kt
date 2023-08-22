package com.carlos.ec03_qrjc.fragments

import android.app.Application
import androidx.lifecycle.*
import com.carlos.ec03_qrjc.data.retrofit.repository.DigimonRepository
import com.carlos.ec03_qrjc.data.retrofit.response.ApiResponse
import com.carlos.ec03_qrjc.db.DigimonDatabase
import com.carlos.ec03_qrjc.model.Digimont
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CouponFavoriteViewModel(application: Application): AndroidViewModel(application){
    private val repository:DigimonRepository
    private val _favorites: MutableLiveData<List<Digimont>> = MutableLiveData()
    val favorites:LiveData<List<Digimont>> = _favorites
    init {
        val db=DigimonDatabase.getDatabase(application)
        repository = DigimonRepository(db.digimonDao())
    }
    fun getFavorites(){
        viewModelScope.launch(Dispatchers.IO) {
            val data = repository.getFavorites()
            _favorites.postValue(data)
        }
    }
}