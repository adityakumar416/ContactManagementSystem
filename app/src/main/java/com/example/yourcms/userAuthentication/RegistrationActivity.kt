package com.example.yourcms.userAuthentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.fragment.findNavController
import com.example.yourcms.R
import com.example.yourcms.databinding.ActivityMainBinding
import com.example.yourcms.databinding.ActivityRegistrationBinding
import com.example.yourcms.databinding.FragmentRegistrationBinding
import com.example.yourcms.userAuthentication.utlis.Constant
import com.example.yourcms.userAuthentication.utlis.PrefManager
import com.google.android.material.snackbar.Snackbar

class RegistrationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistrationBinding
    private lateinit var prefManager: PrefManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        prefManager = PrefManager(this)
        binding.signIn.setOnClickListener {
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish()

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

                Toast.makeText(this,"Registration Successful.", Toast.LENGTH_SHORT).show();




            }


        }

    }
}