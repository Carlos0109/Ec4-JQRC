package com.carlos.ec03_qrjc.fragments

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.carlos.ec03_qrjc.R
import com.carlos.ec03_qrjc.databinding.FragmentInfoBinding
import com.carlos.ec03_qrjc.view.LoginActivity
import com.carlos.ec03_qrjc.view.SplashScreenActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class InfoFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var binding: FragmentInfoBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = requireActivity().getSharedPreferences(
            LoginActivity.SESSION_PREFERENCE,
            AppCompatActivity.MODE_PRIVATE
        )
        firebaseAuth = Firebase.auth
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentInfoBinding.inflate(layoutInflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnCloseSession.setOnClickListener{
            firebaseAuth.signOut()
            sharedPreferences.edit().apply{
                putString(LoginActivity.EMAIL,"")
                    .apply()
            }
            val intent= Intent(requireActivity(),SplashScreenActivity::class.java)
            requireActivity().startActivity(intent)
            requireActivity().finish()
        }
    }
}