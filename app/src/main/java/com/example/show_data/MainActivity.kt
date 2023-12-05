package com.example.show_data

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var dbHelper: DbHelper
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper = DbHelper(this)


    }
    fun onSubmitButtonClick(view: View) {

        val etId=findViewById<EditText>(R.id.etId)
        val etName=findViewById<EditText>(R.id.etName)
        val etEmail=findViewById<EditText>(R.id.etEmail)
        val etContact=findViewById<EditText>(R.id.etContact)
        val etAddress=findViewById<EditText>(R.id.etAddress)

        if (etId.text.isBlank() || etName.text.isBlank() || etEmail.text.isBlank()
            || etContact.text.isBlank() || etAddress.text.isBlank()
        ) {
            Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show()
            return
        }

        // Convert id to Int and handle invalid input
        try {
            etId.text.toString().toInt()
        } catch (e: NumberFormatException) {
            Toast.makeText(this, "Invalid ID", Toast.LENGTH_SHORT).show()
            return
        }


        val name = etName.text.toString()
        val email = etEmail.text.toString()
        val contact = etContact.text.toString()
        val address = etAddress.text.toString()

        val person = Person(id = 0, name = name, email = email, contact = contact, address = address)

        dbHelper.insertPerson(person)

        // Start another activity to display the entered data
        val intent = Intent(this, DisplayActivity::class.java)
        startActivity(intent)


    }
}