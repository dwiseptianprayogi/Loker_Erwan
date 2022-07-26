package com.example.lockererwan

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class LoginActivity : AppCompatActivity() {

    private lateinit var email:TextInputEditText
    private lateinit var pass:TextInputEditText
    lateinit var btnLogin:Button

    lateinit var auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()
        email = findViewById(R.id.etEmailLogin)
        pass = findViewById(R.id.etPasswordLoginVal)
        btnLogin = findViewById(R.id.btnLogin)

        btnLogin.setOnClickListener{
            login()
        }


    }

    override fun onStart() {
        super.onStart()
        val currentUser: FirebaseUser? = auth.getCurrentUser()
        if (currentUser == null) {
//            Toast.makeText(this, "current user null", Toast.LENGTH_SHORT).show()
        } else {
//            startActivity(Intent(this, NavigationBarActivity::class.java))
            startActivity(Intent(this, NavigationBarActivity::class.java))
            finish()
        }
    }

    private fun login() {
        val emailVal = email.text.toString()
        val passVal = pass.text.toString()

        if (emailVal.isEmpty() || passVal.isEmpty()) {
            Toast.makeText(this, "Please make sure to fill in your email or password", Toast.LENGTH_SHORT).show()
            return
        }

        auth.signInWithEmailAndPassword(emailVal,passVal).addOnCompleteListener(this){
            if (it.isSuccessful){
                Toast.makeText(this,"login Succes",Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, NavigationBarActivity::class.java))
                finish()
            } else
                Toast.makeText(this,"failed login", Toast.LENGTH_SHORT).show()
        }
    }
}