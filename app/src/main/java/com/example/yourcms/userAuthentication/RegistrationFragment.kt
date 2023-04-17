package com.example.yourcms.userAuthentication

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.yourcms.R
import com.example.yourcms.databinding.FragmentRegistrationBinding
import com.example.yourcms.showContact.ShowNumberFragment
import com.example.yourcms.userAuthentication.utlis.Constant
import com.example.yourcms.userAuthentication.utlis.PrefManager
import com.google.android.material.snackbar.Snackbar

class RegistrationFragment : Fragment() {
    private lateinit var binding: FragmentRegistrationBinding
    private lateinit var prefManager: PrefManager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegistrationBinding.inflate(layoutInflater, container, false)

        prefManager = PrefManager(requireActivity())
        binding.signIn.setOnClickListener {
            findNavController().navigate(R.id.action_registrationFragment_to_loginFragment)
        }

        val name = binding.nameEditText.text.toString()
        val number = binding.numberEditText.text.toString()
        val password = binding.passwordEditText.text.toString()

        binding.signUpBtn.setOnClickListener {

            if (binding.nameEditText.text!!.isEmpty()) {
                binding.nameEditText.requestFocus()
                Snackbar.make(binding.nameEditText, "Name is Mandatory.", Snackbar.LENGTH_SHORT).show();

            } else if (binding.numberEditText.text!!.isEmpty()) {
                binding.numberEditText.requestFocus()
                Snackbar.make(binding.numberEditText, "Password is Mandatory.", Snackbar.LENGTH_SHORT).show();
            } else if (binding.passwordEditText.text!!.isEmpty()) {
                binding.passwordEditText.requestFocus()
                Snackbar.make(binding.passwordEditText,"Password is Mandatory.", Snackbar.LENGTH_SHORT).show();
            }
            else{
                    prefManager.name(Constant.PREF_IS_NAME,binding.nameEditText.text.toString())
                    prefManager.number(Constant.PREF_IS_NUMBER,binding.numberEditText.text.toString())
                    prefManager.password(Constant.PREF_IS_PASSWORD,binding.passwordEditText.text.toString())

                Toast.makeText(context,"Registration Successful.", Toast.LENGTH_SHORT).show();




            }


        }

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        if(prefManager.getBoolean(Constant.PREF_IS_LOGIN)){
            findNavController().navigate(R.id.action_registrationFragment_to_showNumberFragment)
        }
    }

}