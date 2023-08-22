package com.carlos.ec03_qrjc.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.carlos.ec03_qrjc.R
import com.carlos.ec03_qrjc.databinding.FragmentCouponDetailBinding
import com.carlos.ec03_qrjc.model.Digimont

class CouponDetailFragment : Fragment() {

    private lateinit var binding: FragmentCouponDetailBinding
    val args: CouponDetailFragmentArgs by navArgs()
    private lateinit var digimon: Digimont
    private lateinit var viewModel: CouponDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        digimon = args.digimons
        viewModel = ViewModelProvider(requireActivity()).get(CouponDetailViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCouponDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(binding.root)
            .load(digimon.img)
            .into(binding.imgCoupon)
        binding.txtName.text = digimon.name
        binding.txtLevel.text = digimon.level
        binding.btnAddFavorite.apply {
            visibility = if (digimon.isFavorite) View.GONE else View.VISIBLE
        }
        binding.btnDeleteFavorite.apply {
            visibility = if (digimon.isFavorite) View.VISIBLE else View.GONE
        }
        binding.btnAddFavorite.setOnClickListener{
            digimon.isFavorite = true
            viewModel.addFavorite(digimon, it.context)
        }
        binding.btnDeleteFavorite.setOnClickListener{
            digimon.isFavorite = false
            viewModel.deleteFavorite(digimon, it.context)
        }
    }

}