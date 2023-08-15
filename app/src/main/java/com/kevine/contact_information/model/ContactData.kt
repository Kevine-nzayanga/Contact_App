package com.kevine.contact_information.model

import androidx.room.Entity
import androidx.room.PrimaryKey

//this tells room to create a table out of this data class if tou leave it its contactdata but we can name it contacts

@Entity (tableName = "Contacts")
data class ContactData(
    @PrimaryKey (autoGenerate = true) var contactId:Int,
    var image:String,
    var name:String,
    var phoneNumber:String,
    var email:String
)
