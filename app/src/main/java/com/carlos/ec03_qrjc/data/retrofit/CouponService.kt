package com.carlos.ec03_qrjc.data.retrofit

import com.carlos.ec03_qrjc.data.retrofit.response.DigimonListResponse
import com.carlos.ec03_qrjc.model.Digimont
import retrofit2.http.GET

interface DigiApiService {
    @GET("api/digimon")
    suspend fun getPersonas(): List<Digimont>
}