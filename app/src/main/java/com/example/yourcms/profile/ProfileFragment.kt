package com.example.yourcms.profile

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.yourcms.MainActivity
import com.example.yourcms.R
import com.example.yourcms.databinding.FragmentProfileBinding
import com.example.yourcms.model.ContactData
import com.example.yourcms.userAuthentication.LoginActivity
import com.example.yourcms.userAuthentication.utlis.Constant
import com.example.yourcms.userAuthentication.utlis.PrefManager

class ProfileFragment : Fragment() {
    private var contactList = emptyList<ContactData>()
    private lateinit var binding: FragmentProfileBinding
    private lateinit var prefManager: PrefManager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
            prefManager = PrefManager(requireContext())

        val userName = prefManager.getValue(Constant.PREF_IS_NAME)
        binding.userName.text = userName

        val userPhone = prefManager.getValue(Constant.PREF_IS_NUMBER)
        binding.number.text = "+91 $userPhone"

        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (activity as MainActivity).supportActionBar?.title = ""


        binding.backHome.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_showNumberFragment)
        }


        return binding.root
    }





    init {
            setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater.inflate(R.menu.logout_menu,menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.logout -> {
                prefManager.clear()
                val intent = Intent (activity, LoginActivity::class.java)
                activity?.startActivity(intent)
                activity?.finish()

                Toast.makeText(context,"Logout clicked", Toast.LENGTH_SHORT).show();
            }
        }

        return super.onOptionsItemSelected(item)
    }

}