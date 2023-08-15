package com.kevine.contact_information

import android.app.Application
import android.content.Context

class Contact_InformationApp: Application(){

        companion object{
            lateinit var appContext: Context
        }

        override fun onCreate() {
            super.onCreate()
            appContext=applicationContext
        }

}