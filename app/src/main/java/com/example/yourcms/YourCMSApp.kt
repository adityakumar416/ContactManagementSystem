package com.example.yourcms

import android.app.Application
import android.content.Context
import com.example.yourcms.database.ContactDatabase

class YourCMSApp:Application() {

    companion object{
        private lateinit var instance : YourCMSApp

        fun getContext():Context{
            if (::instance.isInitialized){
                return instance.applicationContext
            }
            throw UninitializedPropertyAccessException()
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        ContactDatabase.initializeDatabase(applicationContext)
    }

}