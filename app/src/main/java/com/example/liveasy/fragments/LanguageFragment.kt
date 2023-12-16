package com.example.liveasy.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.liveasy.R
import com.example.liveasy.databinding.FragmentLanguageBinding

class LanguageFragment : BaseFragment() {
    private val binding by lazy { FragmentLanguageBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnlng.setOnClickListener {
            findNavController().navigate(R.id.action_languageFragment_to_mobileFragment)
        }




    }


}