package com.example.yourcms.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.yourcms.model.ContactData


@Dao
interface ContactDao {

        @Insert
        suspend fun addUser(contactData: ContactData)

        @Update
        suspend fun updateContact(contactData: ContactData)

        @Delete
        suspend fun delete(contactData: ContactData)

        @Query("SELECT * from contact_table order by id ASC")
        fun readAllData(): LiveData<List<ContactData>>

        @Query("DELETE FROM contact_table")
        suspend fun deleteAll()

        @Query ("SELECT * FROM contact_table WHERE name LIKE :search OR number LIKE :search")
        fun searchItem (search : String?):List<ContactData>

}