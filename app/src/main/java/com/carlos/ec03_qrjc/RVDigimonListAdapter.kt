package com.carlos.ec03_qrjc

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.carlos.ec03_qrjc.databinding.ItemCouponBinding
import com.carlos.ec03_qrjc.model.Digimont


class RVDigimonListAdapter(var digimon: List<Digimont>, val onClick:(Digimont)-> Unit): RecyclerView.Adapter<DigimonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DigimonViewHolder {
        val binding = ItemCouponBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DigimonViewHolder(binding, onClick)
    }

    override fun getItemCount(): Int = digimon.size

    override fun onBindViewHolder(holder: DigimonViewHolder, position: Int) {
        holder.bind(digimon[position])
    }
}

class DigimonViewHolder(private val binding: ItemCouponBinding, val onClick:(Digimont)-> Unit) : RecyclerView.ViewHolder(binding.root) {

    fun bind(digimon: Digimont) {
        Glide.with(binding.root)
            .load(digimon.img)
            .into(binding.imageview)
        binding.txtName.text = digimon.name
        binding.txtLevel.text = digimon.level
        binding.root.setOnClickListener{
            onClick(digimon)
        }
    }
}