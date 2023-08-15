package com.kevine.contact_information.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kevine.contact_information.Contact_InformationApp
import com.kevine.contact_information.database.ContactsDb
import com.kevine.contact_information.model.ContactData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ContactsRepository {
    val database= ContactsDb.getDatabase(Contact_InformationApp.appContext)

    suspend fun saveContact(contact:ContactData){
        withContext(Dispatchers.IO){
            database.contactDao().insertContact(contact)
        }
    }
//    why its not a suspend function
    fun getDbContacts():LiveData<List<ContactData>>{
        return database.contactDao().getAllContacts()
    }

    fun getContactById(contactId: Int): LiveData<ContactData> {
        return database.contactDao().getContactById(contactId)
    }




}

//class ContactsRepository {
//    val database = ContactsDb.getDatabase(Contact_InformationApp.appContext)
//    suspend fun saveContact(contact: ContactData) {
//        withContext(Dispatchers.IO) {
//            database.contactDao().insertContact(contact)
//        }
//    }
//    fun fetchContacts(): LiveData<List<ContactData>> {
//        return database.contactDao().getAllContacts()
//    }
//    fun getContactById(contactId: Int): LiveData<ContactData> {
//        return database.contactDao().getContactById(contactId)
//    }
//}