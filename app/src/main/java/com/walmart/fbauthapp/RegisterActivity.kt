package com.walmart.fbauthapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private lateinit var etEmail: EditText
    private lateinit var etPwd: EditText
    private lateinit var btnRegister: Button
    private lateinit var btnCancel: Button
    private lateinit var txtLogin: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = Firebase.auth

        etEmail = findViewById(R.id.editTextRegisterEmail)
        etPwd = findViewById(R.id.editTextRegisterPassword)

        btnRegister = findViewById(R.id.buttonRegister)
        btnRegister.setOnClickListener{

            var email: String = etEmail.text.toString()
            var password: String = etPwd.text.toString()

            //lets create a new user
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, {task ->
                    if (task.isSuccessful){

                        startActivity(Intent(this, MainActivity::class.java))
                        Toast.makeText(this, "User created successfully", Toast.LENGTH_LONG).show()
                        finish()
                    }else{
                        Toast.makeText(this,task.exception.toString(),Toast.LENGTH_LONG).show()
                    }
                })

            etEmail.text = null
            etPwd.text = null

        }

        btnCancel = findViewById(R.id.buttonRegisterCancel)
        btnCancel.setOnClickListener{

            etEmail.text = null
            etPwd.text = null
        }

        txtLogin = findViewById(R.id.textViewLogin)
        txtLogin.setOnClickListener{

            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}