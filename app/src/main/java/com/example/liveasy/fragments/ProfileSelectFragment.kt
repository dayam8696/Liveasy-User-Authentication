package com.example.liveasy.fragments

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.RelativeSizeSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val radioGroup: RadioGroup = binding.yourRadioGroup

        val shipperRadioButton: RadioButton = binding.shiper
        val transporterRadioButton: RadioButton = binding.transorter

        // Modifying text size for Shipper RadioButton
        val spannableShipper = SpannableString(shipperRadioButton.text)

        spannableShipper.setSpan(RelativeSizeSpan(1.5f), 0, 11, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE) // Change text size for first 7 characters
        shipperRadioButton.text = spannableShipper

        // Modifying text size for Transporter RadioButton
        val spannableTransporter = SpannableString(transporterRadioButton.text)
        spannableTransporter.setSpan(RelativeSizeSpan(1.5f), 0, 15, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE) // Change text size for first 11 characters
        transporterRadioButton.text = spannableTransporter


    }

}