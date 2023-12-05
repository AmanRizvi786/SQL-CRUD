package com.example.show_data

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DisplayActivity : AppCompatActivity(), PersonAdapter.OnItemClickListener {
    private lateinit var dbHelper: DbHelper
    private lateinit var persons: List<Person>
    // ... existing code ...

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)

        dbHelper = DbHelper(this)

       persons = dbHelper.getAllPersons()

        // Set up RecyclerView
        val layoutManager = LinearLayoutManager(this)
        val recyclerView=findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = layoutManager

        // Set up the adapter
        val adapter = PersonAdapter(persons,this)
        recyclerView.adapter = adapter

        val btnAlter=findViewById<Button>(R.id.btnAlter)
        btnAlter.setOnClickListener {
            startActivity(Intent(this,EditActivity::class.java))
        }

    }
    override fun onItemClick(position: Int) {
        if (position >= 0 && position < persons.size) {
            val selectedPerson = persons[position]

            val intent = Intent(this, EditActivity::class.java)
            intent.putExtra("personId", selectedPerson.id)
            startActivity(intent)
        }
    }
}