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
//        binding.verifycontbtn.setOnClickListener {
//            val credential = PhoneAuthProvider.getCredential(
        //        MobileFragment.verificationCode
//               binding.verifycontbtn.setOnClickListener {
//                   findNavController().navigate(R.id.action_verifyFragment3_to_profileSelectFragment)
//               }

//            )
//
//        }

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
//        binding.progressBar.visibility = View.VISIBLE

        mAuth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful) {
//                isOtpVerified = true
//                binding.verifyOtp.visibility = View.GONE
                showToast("OTP Verified Successfully")


//               binding.progressBar.visibility = View.GONE
//                binding.resendTimer.visibility = View.GONE
            } else {
                showToast("OTP Verification Failed")
//                binding.progressBar.visibility = View.GONE
            }
        }
    }

}