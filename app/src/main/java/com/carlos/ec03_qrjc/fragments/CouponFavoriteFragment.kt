package com.carlos.ec03_qrjc.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.carlos.ec03_qrjc.RVDigimonListAdapter
import com.carlos.ec03_qrjc.databinding.FragmentCouponFavoriteBinding

class CouponFavoriteFragment : Fragment() {

    private lateinit var binding:FragmentCouponFavoriteBinding
    private lateinit var viewModel: CouponFavoriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(CouponFavoriteViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCouponFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = RVDigimonListAdapter(listOf()){digimont ->
            val couponDetailDirection = CouponFavoriteFragmentDirections.
            actionCouponFavoriteFragmentToCouponDetailFragment(digimont)
            findNavController().navigate(couponDetailDirection)
        }
        binding.rvCouponList.adapter = adapter
        binding.rvCouponList.layoutManager = GridLayoutManager(requireContext(),2,RecyclerView.VERTICAL,false)
        viewModel.favorites.observe(requireActivity()){
            adapter.digimon = it
            adapter.notifyDataSetChanged()
        }
        viewModel.getFavorites()
    }
}