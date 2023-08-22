package com.carlos.ec03_qrjc.fragments

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.carlos.ec03_qrjc.model.DigimonFirebase
import com.google.firebase.firestore.FirebaseFirestore

class DigimonListBDViewModel(application: Application): AndroidViewModel(application) {

    private val database = FirebaseFirestore.getInstance()
    private val couponsRef = database.collection("digimon")

    val couponsLiveData = MutableLiveData<List<DigimonFirebase>>()

    init {
        couponsRef.addSnapshotListener { snapshot, error ->
            if (error != null) {
                // Handle error
                return@addSnapshotListener
            }

            val coupons = mutableListOf<DigimonFirebase>()
            for (couponSnapshot in snapshot?.documents ?: emptyList()) {
                val coupon = couponSnapshot.toObject(DigimonFirebase::class.java)
                coupon?.let {
                    coupons.add(it)
                }
            }
            couponsLiveData.value = coupons
        }
    }
    }/*
    val repository = CouponsRepository()
    val couponList: MutableLiveData<List<Coupon>> = MutableLiveData<List<Coupon>>()

    fun getCouponList(){
        val data = getData()
        couponList.value = data
    }
    fun getCouponsFromService(){
        viewModelScope.launch(Dispatchers.IO){
            val response= repository.getCoupons()
            when(response){
                is ApiResponse.Error ->{
                    // colocar error
                }
                is ApiResponse.Success ->{
                    couponList.postValue(response.data.coupons)
                }
            }
        }
    }*/
