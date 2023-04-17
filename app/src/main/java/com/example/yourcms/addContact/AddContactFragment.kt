package com.example.yourcms.addContact

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.yourcms.databinding.FragmentAddContactBinding
import com.example.yourcms.model.ContactData
import com.example.yourcms.viewModel.ContactViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddContactFragment : Fragment() {
private lateinit var binding:FragmentAddContactBinding
    val contactViewModel : ContactViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAddContactBinding.inflate(layoutInflater, container, false)



        binding.saveContact.setOnClickListener {

            val name = binding.addNameEditText.text.toString()
            val number = binding.addNumberEditText.text.toString()

            if (binding.addNameEditText.text!!.isEmpty()) {
                binding.addNameEditText.requestFocus()
                Snackbar.make(binding.addNameEditText, "Name is Mandatory.", Snackbar.LENGTH_SHORT).show();

            } else if (binding.addNumberEditText.text!!.isEmpty()) {
                binding.addNumberEditText.requestFocus()
                Snackbar.make(binding.addNumberEditText, "Password is Mandatory.", Snackbar.LENGTH_SHORT).show();
            }
            else{

                val contactData = ContactData(0,name,number)

                lifecycleScope.launch{
                    contactViewModel.addUser(contactData)
                }
                Toast.makeText(context,"User is Save", Toast.LENGTH_SHORT).show()

            }
        }
        return binding.root
    }

}