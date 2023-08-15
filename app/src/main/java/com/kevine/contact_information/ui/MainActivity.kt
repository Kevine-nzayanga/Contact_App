package com.kevine.contact_information.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.kevine.contact_information.model.ContactData
import com.kevine.contact_information.ContactrvAdapter
import com.kevine.contact_information.R
import com.kevine.contact_information.databinding.ActivityMainBinding
import com.kevine.contact_information.viewmodel.ContactsViewModel

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val contactsViewModel:ContactsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        contactsViewModel.getContacts().observe(this, {contacts -> DisplayContacts(contacts)})
        binding.fab.setOnClickListener {
            startActivity(Intent(this,Add_contact::class.java))
        }

    }

    fun DisplayContacts(contactList:List<ContactData>){

        binding.rvcontacts.layoutManager=LinearLayoutManager(this)
        var contactAdapter= ContactrvAdapter(contactList, this)
        binding.rvcontacts.adapter=contactAdapter

    }

}
