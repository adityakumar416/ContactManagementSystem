package com.example.yourcms.userAuthentication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.yourcms.R
import com.example.yourcms.databinding.ActivityLoginBinding
import com.example.yourcms.databinding.ActivityMainBinding
import com.example.yourcms.databinding.FragmentLoginBinding
import com.example.yourcms.userAuthentication.utlis.Constant
import com.example.yourcms.userAuthentication.utlis.PrefManager
import com.google.android.material.snackbar.Snackbar

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var prefManager: PrefManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)



        prefManager = PrefManager(requireActivity())

        binding.signUp.setOnClickListener {

            findNavController().navigate(R.id.registrationFragment)

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
    }
}