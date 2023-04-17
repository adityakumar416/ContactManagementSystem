package com.example.yourcms.showContact

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yourcms.R
import com.example.yourcms.databinding.FragmentShowNumberBinding
import com.example.yourcms.model.ContactData
import com.example.yourcms.userAuthentication.utlis.Constant
import com.example.yourcms.userAuthentication.utlis.PrefManager
import com.example.yourcms.viewModel.ContactViewModel
import kotlinx.coroutines.launch

class ShowNumberFragment : Fragment(),DeleteContact {
 private lateinit var binding: FragmentShowNumberBinding
    private lateinit var prefManager: PrefManager
    private val contactViewModel : ContactViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentShowNumberBinding.inflate(layoutInflater, container, false)

        prefManager = PrefManager(requireContext())

        val name =prefManager.getValue(Constant.PREF_IS_NAME)

        binding.userName.text ="Hello, $name !"

        val adapter =ShowNumberAdapter()
        adapter.deleteContact = this
        val recyclerView = binding.recyclerView
        recyclerView.adapter = adapter

        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val dataObserver = Observer<List<ContactData>>{
            newList -> adapter.setData(newList)
        }


        contactViewModel.viewAllData.observe(viewLifecycleOwner,dataObserver)

        binding.profileIcon.setOnClickListener {
            findNavController().navigate(R.id.action_showNumberFragment_to_profileFragment)
        }

        binding.addContact.setOnClickListener {
            findNavController().navigate(R.id.action_showNumberFragment_to_addContactFragment)
        }



        return binding.root
    }


      override fun deleteContact(contactData: ContactData) {

          val alertDialog = AlertDialog.Builder(context)

          alertDialog.setTitle("Delete Contact")
          alertDialog.setMessage("Do you want to delete this Contact ?")
          alertDialog.setPositiveButton("Yes") { dialog, hello ->
              lifecycleScope.launch{
                  contactViewModel.deleteContact(contactData)
              }
              dialog.dismiss()
          }
          alertDialog.setNegativeButton("No") { dialog, hello ->
              dialog.dismiss()
          }

          val alertDialogShow = alertDialog.create()

          alertDialogShow.show()

    }

}