package com.example.liveasy.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.liveasy.R
import com.example.liveasy.databinding.FragmentVerifyPageBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider

open class VerifyFragment :BaseFragment() {
    private val binding by lazy { FragmentVerifyPageBinding.inflate(layoutInflater) }

    private val mAuth = FirebaseAuth.getInstance()
    companion object{
        lateinit var binding: FragmentVerifyPageBinding
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()

        binding.phone.text = "Code is sent to ${MobileFragment.mobileNo}"
    }

    private fun setupClickListeners() {


        binding.otpView.setOtpCompletionListener {otp->
            Log.d("Actual Value", otp)
            val credential = PhoneAuthProvider.getCredential(
                MobileFragment.verificationCode,
                otp
            )

            verifyOtp(credential)
            binding.verifycontbtn.setOnClickListener {
                findNavController().navigate(R.id.action_verifyFragment3_to_profileSelectFragment)
            }

        }


    }

    private fun verifyOtp(credential: PhoneAuthCredential) {


        mAuth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful) {

                showToast("OTP Verified Successfully")



            } else {
                showToast("OTP Verification Failed")

            }
        }
    }

}