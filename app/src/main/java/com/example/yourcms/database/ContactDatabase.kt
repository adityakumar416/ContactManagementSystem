package com.example.yourcms.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.yourcms.model.ContactData


@Database(
    entities =[ContactData::class],
    version = 3,
    exportSchema = true
)

abstract class ContactDatabase : RoomDatabase() {
    abstract fun contactDao(): ContactDao

    companion object{

        @Volatile
        private lateinit var instance :ContactDatabase

        fun getDatabase(): ContactDatabase{

            if(::instance.isInitialized)
                return instance

            throw UninitializedPropertyAccessException()
        }

        fun initializeDatabase(context: Context){

            synchronized(this){
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    ContactDatabase::class.java,
                    "contact_database"
                ).build()
            }

        }

    }


}