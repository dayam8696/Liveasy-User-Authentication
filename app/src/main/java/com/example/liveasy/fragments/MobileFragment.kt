package com.example.liveasy.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.liveasy.R
import com.example.liveasy.databinding.FragmentMobileBinding

class MobileFragment: Fragment(){
    private val binding by lazy { FragmentMobileBinding.inflate(layoutInflater) }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
   binding.contbtn.setOnClickListener {
       findNavController().navigate(R.id.action_mobileFragment_to_verifyFragment3)
   }
        }
    }

