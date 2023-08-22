package com.carlos.ec03_qrjc.data.retrofit.repository

import com.carlos.ec03_qrjc.data.retrofit.RetrofitInstance
import com.carlos.ec03_qrjc.data.retrofit.response.ApiResponse
import com.carlos.ec03_qrjc.db.DigimonDao
import com.carlos.ec03_qrjc.model.Digimont


class DigimonRepository(val digimonDao: DigimonDao?=null) {
    suspend fun getDigimons(): ApiResponse<List<Digimont>> {
        return try {
            val response = RetrofitInstance.getCouponService().getPersonas()
            ApiResponse.Success(response)
        } catch (e: Exception) {
            ApiResponse.Error(e)
        }
    }
    suspend fun addFavorite(digimon: Digimont){
            digimonDao?.let{
                it.addFavorite(digimon)
            }

    }
    fun getFavorites(): List<Digimont>{

            digimonDao?.let {
                return it.getFavorites()
            } ?: kotlin.run {
                return listOf()
            }

    }

    suspend fun deleteFavorite(digimon: Digimont) {
        digimonDao?.let {
            it.deleteById(digimon.id)
        }
    }
}