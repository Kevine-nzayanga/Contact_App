package com.kevine.contact_information.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kevine.contact_information.model.ContactData

@Dao
interface ContactDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertContact(contact:ContactData)

    @Query("SELECT * FROM contacts ORDER BY name")
    fun getAllContacts():LiveData<List<ContactData>>

    @Query("SELECT * FROM Contacts WHERE contactId = :contactId")
    fun getContactById(contactId: Int): LiveData<ContactData>
//
//    @Delete
//    suspend fun deleteContact(contact: ContactData)
    @Query("DELETE FROM contacts WHERE contactId = :contactId")
    fun deleteContact(contactId: Int)

}