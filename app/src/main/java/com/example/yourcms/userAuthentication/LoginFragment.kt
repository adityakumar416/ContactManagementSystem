package com.example.yourcms.userAuthentication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.yourcms.R
import com.example.yourcms.databinding.FragmentLoginBinding
import com.example.yourcms.userAuthentication.utlis.Constant
import com.example.yourcms.userAuthentication.utlis.PrefManager
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.make

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var prefManager:PrefManager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)



        prefManager = PrefManager(requireActivity())

        binding.signUp.setOnClickListener {

            findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)

        }

        binding.loginBtn.setOnClickListener {

            if (binding.numberEditText.text!!.isEmpty()) {
                binding.numberEditText.requestFocus()
                Snackbar.make(
                    binding.numberEditText,
                    "Password is Mandatory.",
                    Snackbar.LENGTH_SHORT
                ).show();
            } else if (binding.passwordEditText.text!!.isEmpty()) {
                binding.passwordEditText.requestFocus()
                Snackbar.make(
                    binding.passwordEditText,
                    "Password is Mandatory.",
                    Snackbar.LENGTH_SHORT
                ).show();
            } else {

                val number = binding.numberEditText.text.toString()
                val password = binding.passwordEditText.text.toString()


                val checkNumber = prefManager.getValue(Constant.PREF_IS_NUMBER)
                val checkPassword = prefManager.getValue(Constant.PREF_IS_PASSWORD)

                if(number == checkNumber && checkPassword==password){
                    findNavController().navigate(R.id.showNumberFragment)
                    prefManager.checkLogin(Constant.PREF_IS_LOGIN, true)
                }
                else{
                    Toast.makeText(context,"Please Registration.", Toast.LENGTH_SHORT).show();

                }

            }

        }


        return binding.root
    }
    override fun onStart() {
        super.onStart()
        if(prefManager.getBoolean(Constant.PREF_IS_LOGIN)){
            findNavController().navigate(R.id.showNumberFragment)
        }
    }

}