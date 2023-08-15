package com.kevine.contact_information.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.room.InvalidationTracker
import com.kevine.contact_information.R
import com.kevine.contact_information.databinding.ActivityAddContactBinding
import com.kevine.contact_information.databinding.ActivityContactDetailsBinding
import com.kevine.contact_information.model.ContactData
import com.kevine.contact_information.repository.ContactsRepository
import com.kevine.contact_information.viewmodel.ContactsViewModel

class ContactDetailsActivity<TextView> : AppCompatActivity() {
    var contactId=0
    private val viewModel: ContactsViewModel by viewModels()
    lateinit var binding: ActivityContactDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var bundle = intent.extras

        if (bundle!=null){
            contactId=bundle.getInt("CONTACT_ID")
            Toast.makeText(this,"$contactId",Toast.LENGTH_SHORT).show()
        }

        }

    override fun onResume(){
        super.onResume()
        contactdetails()

    }

//    fun contactdetails() {
//        viewModel.getContactById(contactId).observe(this, Observer { singlecontact ->
//            binding.tvnamedetails.text = singlecontact.name
//            binding.tvnumdetails.text = singlecontact.phoneNumber
//            binding.tvemaildetails.text = singlecontact.email
//        })
//    }

    fun contactdetails() {

binding.tvnamedetails.text = ContactData.name
binding.tvnumdetails.text = singlecontact.phoneNumber
binding.tvemaildetails.text = singlecontact.email

viewModel.getContactById(contactId)
?
//    fun contactdetails() {
//        viewModel.getContactById(contactId).(this, { singlecontact ->
//            binding.tvnamedetails.text = singlecontact.name
//            binding.tvnumdetails.text = singlecontact.phoneNumber
//            binding.tvemaildetails.text = singlecontact.email
//        })
//    }

}




//fun DisplayDetails(contactDetails: ContactData){
//    if (contactDetails!=null){
//        var namedetails= binding.tvnamedetails.text.toString()
//        var emaildetails = binding.tvemaildetails.text.toString()
//        namedetails = findViewById(R.id.tvname);
//
//    }
//}