package com.example.yourcms.repository

import android.icu.text.StringSearch
import androidx.lifecycle.LiveData
import com.example.yourcms.database.ContactDatabase
import com.example.yourcms.model.ContactData

class ContactRepository {

    private val database: ContactDatabase by lazy {
        ContactDatabase.getDatabase()
    }

    val viewAllData : LiveData<List<ContactData>> =database.contactDao().readAllData()

    suspend fun addContact(contactData: ContactData){
            database.contactDao().addUser(contactData)
    }

    suspend fun updateContact(contactData: ContactData){
        database.contactDao().updateContact(contactData)
    }
    suspend fun deleteContact(contactData: ContactData){
        database.contactDao().delete(contactData)
    }

    suspend fun deleteAllContact(){
        database.contactDao().deleteAll()
    }

    suspend fun searchItem(search: String) {
        database.contactDao().searchItem(search)
    }

}