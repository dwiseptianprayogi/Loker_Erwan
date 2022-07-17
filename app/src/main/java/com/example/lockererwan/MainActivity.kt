package com.example.lockererwan

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main11)

        val adapter = ArrayAdapter.createFromResource(this, R.array.city_list, android.R.layout.simple_spinner_dropdown_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        findViewById<Spinner>(R.id.spinner2).adapter = adapter

        val btn_bukalocker = findViewById(R.id.bukalocker) as Button
        btn_bukalocker.setOnClickListener {
            Toast.makeText(this, "Locker terbuka: " + findViewById<Spinner>(R.id.spinner2).selectedItem.toString(), Toast.LENGTH_LONG).show()
        }
     //   val btn_simpan = findViewById(R.id.simpan) as Button


    }
}


