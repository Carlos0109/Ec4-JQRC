package com.carlos.ec03_qrjc

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.carlos.ec03_qrjc.databinding.ItemCouponBinding
import com.carlos.ec03_qrjc.db.DigimonDatabase
import com.carlos.ec03_qrjc.model.DigimonFirebase

class RVCouponBDListAdapter(var coupons:List<DigimonFirebase>, val onClick:(DigimonFirebase)-> Unit):RecyclerView.Adapter<CouponBD>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CouponBD {
        val binding = ItemCouponBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CouponBD(binding, onClick)
    }

    override fun getItemCount(): Int = coupons.size

    override fun onBindViewHolder(holder: CouponBD, position: Int) {
        holder.bind(coupons[position])
    }
}

class CouponBD(private val binding: ItemCouponBinding, val onClick: (DigimonFirebase)->Unit):RecyclerView.ViewHolder(binding.root){
    fun bind(coupon: DigimonFirebase){
        Glide.with(binding.root)
            .load(coupon.img)
            .into(binding.imageview)
        binding.txtName.text = coupon.name
        binding.txtLevel.text = coupon.level
        binding.root.setOnClickListener{
            onClick(coupon)
        }
    }
}