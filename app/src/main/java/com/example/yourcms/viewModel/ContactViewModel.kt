package com.example.yourcms.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.yourcms.model.ContactData
import com.example.yourcms.repository.ContactRepository

class ContactViewModel:ViewModel() {

    private var repository:ContactRepository
    var viewAllData : LiveData<List<ContactData>>

    init {
        repository = ContactRepository()
        viewAllData =repository.viewAllData
    }
        suspend fun addUser(contactData: ContactData){
            repository.addContact(contactData)
        }

        suspend fun updateContact(contactData: ContactData){
            repository.updateContact(contactData)
        }

    suspend fun deleteContact(contactData: ContactData){
        repository.deleteContact(contactData)
    }
    suspend fun deleteAllContact(){
        repository.deleteAllContact()
    }
    suspend fun searchItem(search: String){
        repository.searchItem(search)
    }

}