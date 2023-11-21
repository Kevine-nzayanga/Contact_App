package com.kevine.contact_information.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kevine.contact_information.model.ContactData
import com.kevine.contact_information.repository.ContactsRepository
import kotlinx.coroutines.launch

class ContactsViewModel:ViewModel() {
    val contactsRepo= ContactsRepository()

  fun saveContact(contact:ContactData){
        viewModelScope.launch {
            contactsRepo.saveContact(contact)
        }
    }

    fun getContacts():LiveData<List<ContactData>>{
        return contactsRepo.getDbContacts()
    }

    fun getContactById(contactId: Int):LiveData<ContactData> {
      return  contactsRepo.getContactById(contactId)
    }

    fun deletecontact(contactId: Int) {

        viewModelScope.launch{
        contactsRepo.deletecontact(contactId)
    }}

}