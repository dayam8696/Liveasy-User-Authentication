package com.example.liveasy.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.liveasy.R
import com.example.liveasy.databinding.FragmentMobileBinding
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.i18n.phonenumbers.PhoneNumberUtil
import com.google.i18n.phonenumbers.Phonenumber
import java.util.concurrent.TimeUnit

open class MobileFragment : BaseFragment()  {
    private val binding by lazy { FragmentMobileBinding.inflate(layoutInflater) }
    private val mAuth = FirebaseAuth.getInstance()

    private var isResend = false

    companion object {
        lateinit var verificationCode: String
        lateinit var resendingToken: PhoneAuthProvider.ForceResendingToken
        lateinit var mobileNo: String
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClicklisteneres()

    }

    private fun setupClicklisteneres() {
        binding.contbtn.setOnClickListener {

            sendOtpAndNavigate()
        }




    }

    private fun sendOtpAndNavigate() {
        if (isPhoneNumberValid(
                binding.editTextPhoneNumber.text.toString().trim(),
                binding.countryCode.selectedCountryCode
            )
        ) {
            sendOtp(
                binding.countryCode.selectedCountryCode,
                binding.editTextPhoneNumber.text.toString().trim()
            )
//            findNavController().navigate(R.id.action_mobileFragment_to_verifyFragment3)
        } else showToast("not ok")
    }

    private fun isPhoneNumberValid(phoneNumber: String, countryCode: String): Boolean {
        val phoneNumberUtil = PhoneNumberUtil.getInstance()
        val phoneNumberProto = Phonenumber.PhoneNumber()
        return try {
            phoneNumberProto.countryCode = countryCode.toInt()
            phoneNumberProto.nationalNumber = phoneNumber.toLong()
            phoneNumberUtil.isValidNumber(phoneNumberProto)
        } catch (e: NumberFormatException) {
            false
        }
    }

    private fun sendOtp(countryCode: String, phoneNumber: String) {
        val builder = PhoneAuthOptions.newBuilder(mAuth)
            .setPhoneNumber("+$countryCode$phoneNumber")
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(requireActivity())
            .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                override fun onVerificationCompleted(credential: PhoneAuthCredential) {
//                    verifyOtp(credential)
                   // binding.progressBar.visibility = View.GONE
                }

                override fun onVerificationFailed(p0: FirebaseException) {
                    Log.d("verification", "onVerificationFailed: ${p0.localizedMessage}")
                    showToast("Too many requests \nTry again after some time")
//                    binding.progressBar.visibility = View.GONE
                }

                override fun onCodeSent(
                    p0: String,
                    forceResendingToken: PhoneAuthProvider.ForceResendingToken
                ) {
                    super.onCodeSent(p0, forceResendingToken)
                    verificationCode = p0
                    resendingToken = forceResendingToken
                    Log.d("verification", "onVerificationFailed: ${p0}")

//                    isResend = true
               //   binding.progressBar.visibility = View.VISIBLE
                    showToast("OTP Sent Successfully")
                    mobileNo = binding.editTextPhoneNumber.text.toString()
                    findNavController().navigate(R.id.action_mobileFragment_to_verifyFragment3)
//                    binding.progressBar.visibility = View.GONE
//                    startResendTimer()
                }
            })

        if (isResend) {
            PhoneAuthProvider.verifyPhoneNumber(
                builder.setForceResendingToken(resendingToken).build()
            )
        } else {
            Log.d("phoneno", "+$countryCode$phoneNumber")
            PhoneAuthProvider.verifyPhoneNumber(
                builder.build()
            )
        }
    }
}


