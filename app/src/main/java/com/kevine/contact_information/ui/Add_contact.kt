package com.kevine.contact_information.ui

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.location.Location
import android.location.LocationRequest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.content.FileProvider
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import android.Manifest
import android.os.Looper
import com.kevine.contact_information.R
import com.kevine.contact_information.databinding.ActivityAddContactBinding
import com.kevine.contact_information.model.ContactData
import com.kevine.contact_information.viewmodel.ContactsViewModel
import java.io.File

class Add_contact : AppCompatActivity() {
    lateinit var binding: ActivityAddContactBinding
    private val contactsViewModel:ContactsViewModel by viewModels()
    lateinit var photoFile: File
    lateinit var fusedLocationClient:FusedLocationProviderClient
    lateinit var mylocation: Location


    val cameraLauncher=registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()) { result->
        if (result.resultCode == Activity.RESULT_OK){
            val photo= BitmapFactory.decodeFile(photoFile.absolutePath)
            binding.imageView5.setImageBitmap(photo)
        }

    }

    var locationRequest =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { result ->
            if (checkSelfPermission(android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                getLastKnownLocation()

            } else {
                Toast.makeText(this, "Please grant location permission", Toast.LENGTH_LONG).show()

            }
        }



    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityAddContactBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

    }

    override fun onResume() {
        super.onResume()
        binding.btsave.setOnClickListener {
            validateRegisterContacts()
            startActivity(Intent(this,MainActivity::class.java))
        }

        binding.imageView5.setOnClickListener {
            capturePhoto()
        }
        getLastKnownLocation()

    }

    fun getLastKnownLocation(){
        if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)==PackageManager.PERMISSION_GRANTED) {
            val locationRequest=com.google.android.gms.location.LocationRequest.Builder(1000)
                .setPriority(Priority.PRIORITY_HIGH_ACCURACY)
                .build()

            val locationCallBack = object : LocationCallback(){
                override fun onLocationResult(p0: LocationResult) {
                    super.onLocationResult(p0)
                    for (location in p0.locations){
                        Toast.makeText(baseContext,
                            "Lat: ${location.latitude}, Long: ${location.longitude}",
                            Toast.LENGTH_LONG).show()
                    }

                }
            }

            fusedLocationClient.requestLocationUpdates(locationRequest, locationCallBack, Looper.getMainLooper())

        }
        else{
            locationRequest.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
        }


//            fusedLocationClient.getCurrentLocation(Priority.PRIORITY_BALANCED_POWER_ACCURACY,CancellationTokenSource().token)
//                .addOnSuccessListener { location ->
//                mylocation=location
//                Toast.makeText(
//                    this, "Lat:${location.latitude}, Long:${location.longitude}",
//                    Toast.LENGTH_LONG
//                ).show()
//
//            }
    }

    private fun capturePhoto(){
      var photoIntent=Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        photoFile=getPhotoFile("photo_${System.currentTimeMillis()}")
        val fileUri = FileProvider.getUriForFile(this,"com.kevine.contact_information.provider",photoFile)
        photoIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri)

        cameraLauncher.launch(photoIntent)

    }

    private fun getPhotoFile(fileName:String):File{
        val folder= getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        var tempFile=File.createTempFile(fileName,".jpeg",folder)
        return tempFile
    }




    fun validateRegisterContacts() {
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
            val newContact= ContactData(contactId = 0,name=name, phoneNumber = contacts, email = email, image = photoFile.absolutePath)
           contactsViewModel.saveContact(newContact)
            Toast.makeText(this,"$contacts is $name", Toast.LENGTH_LONG).show()
           finish()
        }

    }
}
