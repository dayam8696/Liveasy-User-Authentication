package com.example.liveasy.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import com.example.liveasy.R
import com.example.liveasy.databinding.ProfileSelectFragmentBinding

class ProfileSelectFragment : BaseFragment() {
    private val binding by lazy { ProfileSelectFragmentBinding.inflate(layoutInflater) }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root

    }


}