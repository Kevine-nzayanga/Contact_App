package com.kevine.contact_information.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import com.kevine.contact_information.R
import com.kevine.contact_information.databinding.ActivityAddContactBinding
import com.kevine.contact_information.model.ContactData
import com.kevine.contact_information.viewmodel.ContactsViewModel

class Add_contact : AppCompatActivity() {
    lateinit var binding: ActivityAddContactBinding
    private val contactsViewModel:ContactsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityAddContactBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        binding.btsave.setOnClickListener {
            validateRegisterContacts()
            startActivity(Intent(this,MainActivity::class.java))
        }
    }


    fun validateRegisterContacts(){
        val name =binding. tinameentry.text.toString()
        val contacts = binding.tinumber.text.toString()
        val email =binding. tiemail.text.toString()

        var error = false
        if (name.isBlank()){
            binding. tlname.error = "name is required"
            error = true
        }

        if (contacts.isBlank()){
            binding.tinumber.error = getString(R.string.contacts_is_required)
            error = true
        }

        if (email.isBlank()){
            binding.tiemail.error = getString(R.string.email_is_required)
            error = true
        }


        if (!error){
            val newContact= ContactData(contactId = 0,name=name, phoneNumber = contacts, email = email, image = "")
           contactsViewModel.saveContact(newContact)
            Toast.makeText(this,"$contacts is $name", Toast.LENGTH_LONG).show()
           finish()
        }

    }



//    fun clearErrorOnType(){
//        binding.tinameentry.addTextChangedListener ( object : TextWatcher {
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//                binding.tlname.error = null
//
//            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                binding.tlname.error = null
//            }
//
//            override fun afterTextChanged(s: Editable?) {
//
//            }
//        } )
//        binding.tinumber.addTextChangedListener ( object : TextWatcher {
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//                binding.tlnum.error = null
//
//            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                binding.tlnum.error = null
//            }
//
//            override fun afterTextChanged(s: Editable?) {
//
//            }
//        } )
//        binding.tiemail.addTextChangedListener ( object : TextWatcher {
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//                binding.tlemail.error = null
//
//            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                binding.tlemail.error = null
//            }
//
//            override fun afterTextChanged(s: Editable?) {
//
//            }
//        } )
//    }
}
