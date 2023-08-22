package com.carlos.ec03_qrjc.fragments

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.*
import com.carlos.ec03_qrjc.data.retrofit.repository.DigimonRepository
import com.carlos.ec03_qrjc.db.DigimonDatabase
import com.carlos.ec03_qrjc.model.Digimont
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CouponDetailViewModel(application: Application): AndroidViewModel(application) {
    private val repository: DigimonRepository
    init {
        val db= DigimonDatabase.getDatabase(application)
        repository = DigimonRepository(db.digimonDao())
    }
    fun addFavorite(digimon: Digimont, context: Context){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addFavorite(digimon)
            withContext(Dispatchers.Main){
                Toast.makeText(context, "Coupon added to favorite", Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun deleteFavorite(digimon: Digimont, context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteFavorite(digimon)
            withContext(Dispatchers.Main) {
                Toast.makeText(context, "Coupon removed from favorites", Toast.LENGTH_SHORT).show()
            }
        }
    }
}