package com.kevine.contact_information

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kevine.contact_information.databinding.ContactListBinding
import com.kevine.contact_information.model.ContactData
import com.kevine.contact_information.ui.ContactDetailsActivity
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import java.io.File

class ContactrvAdapter (var contactList: List<ContactData>,val context:Context):RecyclerView.Adapter<ContactViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        var binding = ContactListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ContactViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        var contact=contactList[position]

        holder.binding.apply {
            tvname.text = contact.name
            tvemail.text = contact.email
            tvphone.text = contact.phoneNumber

            if (contact.image.isNotBlank()) {
                Picasso
                    .get()
                    .load(File(contact.image))
                    .resize(80, 80)
                    .centerCrop()
                    .transform(CropCircleTransformation())
                    .into(holder.binding.ivprofile)
            }
            cvContact.setOnClickListener{
                val intent = Intent(context, ContactDetailsActivity::class.java)
                intent.putExtra("CONTACT_ID",contact.contactId)
                context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int {
        return contactList.size
    }

}

class ContactViewHolder(var binding: ContactListBinding):RecyclerView.ViewHolder(binding.root){

}

