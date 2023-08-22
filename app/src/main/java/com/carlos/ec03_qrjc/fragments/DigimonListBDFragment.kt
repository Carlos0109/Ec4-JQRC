package com.carlos.ec03_qrjc.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.carlos.ec03_qrjc.R
import com.carlos.ec03_qrjc.RVCouponBDListAdapter
import com.carlos.ec03_qrjc.databinding.FragmentDigimonListBDBinding

class DigimonListBDFragment : Fragment() {

    private lateinit var binding: FragmentDigimonListBDBinding
    private lateinit var viewModel: DigimonListBDViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(DigimonListBDViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDigimonListBDBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = RVCouponBDListAdapter(listOf()){

        }

        binding.rvCouponBd.adapter = adapter
        binding.rvCouponBd.layoutManager = GridLayoutManager(requireContext(),2, RecyclerView.VERTICAL, false)
        viewModel.couponsLiveData.observe(viewLifecycleOwner) { coupons ->
            adapter.coupons = coupons
            adapter.notifyDataSetChanged()
        }
    }
}