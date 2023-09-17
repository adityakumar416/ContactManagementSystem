package com.example.yourcms.update

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.yourcms.databinding.FragmentUpdateDetailBinding
import com.example.yourcms.model.ContactData
import com.example.yourcms.viewModel.ContactViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class UpdateDetailFragment : Fragment() {

    private lateinit var binding: FragmentUpdateDetailBinding
    private val args by navArgs<UpdateDetailFragmentArgs>()
    private val contactViewModel: ContactViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUpdateDetailBinding.inflate(layoutInflater, container, false)

        binding.updateNameEditText.setText(args.currentUser.name)
        binding.updateNumberEditText.setText(args.currentUser.number)

        binding.updateContact.setOnClickListener {

            val name = binding.updateNameEditText.text.toString()
            val number = binding.updateNumberEditText.text.toString()

            if (binding.updateNameEditText.text!!.isEmpty()) {
                binding.updateNameEditText.requestFocus()
                Snackbar.make(
                    binding.updateNameEditText,
                    "Name is Mandatory.",
                    Snackbar.LENGTH_SHORT
                ).show()

            } else if (binding.updateNumberEditText.text!!.isEmpty()) {
                binding.updateNumberEditText.requestFocus()
                Snackbar.make(
                    binding.updateNumberEditText,
                    "Password is Mandatory.",
                    Snackbar.LENGTH_SHORT
                ).show()
            } else {

                val contactData = ContactData(args.currentUser.id, name, number)

                lifecycleScope.launch {
                    contactViewModel.updateContact(contactData)
                }
                Toast.makeText(requireContext(), "User is Update", Toast.LENGTH_SHORT).show()
                findNavController().popBackStack()
            }


        }




        return binding.root
    }
}