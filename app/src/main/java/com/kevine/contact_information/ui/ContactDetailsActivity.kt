package com.kevine.contact_information.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.LiveData
import androidx.room.InvalidationTracker
import com.kevine.contact_information.R
import com.kevine.contact_information.databinding.ActivityAddContactBinding
import com.kevine.contact_information.databinding.ActivityContactDetailsBinding
import com.kevine.contact_information.model.ContactData
import com.kevine.contact_information.repository.ContactsRepository
import com.kevine.contact_information.viewmodel.ContactsViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ContactDetailsActivity<TextView> : AppCompatActivity() {
    var contactId = 0
    private val viewModel: ContactsViewModel by viewModels()
    lateinit var binding: ActivityContactDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.ivdelete.setOnClickListener {
      deleteContact(contactId)
        }


        val contactId = intent.getIntExtra("CONTACT_ID", 0)
        viewModel.getContactById(contactId).observe(this, Observer <ContactData> { contact ->
            if (contact != null) {
                displayContactDetails(contact)
            } else {
                Toast.makeText(this, "Contact not found", Toast.LENGTH_SHORT).show()
            }
        })

    }

     fun displayContactDetails(contact: ContactData) {
        binding.tvnamedetails.text = contact.name
        binding.tvnumdetails.text = contact.phoneNumber
        binding.tvemaildetails.text = contact.email
    }

     fun deleteContact(contactId: Int) {

        viewModel.deletecontact(contactId)
         Toast.makeText(this,"Successfully deleted",Toast.LENGTH_SHORT).show()
    }

}
