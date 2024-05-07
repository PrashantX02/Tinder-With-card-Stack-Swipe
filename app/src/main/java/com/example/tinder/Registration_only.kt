package com.example.tinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

import org.w3c.dom.Text

class Registration_only : AppCompatActivity() {

    lateinit var next: TextView
    lateinit var username : EditText
    lateinit var password :EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                        WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_registration_only)

        val intent = Intent(applicationContext,Verification_email::class.java)
        next = findViewById(R.id.next)

        username =findViewById(R.id.username)
        password =findViewById(R.id.password)

        next.setOnClickListener{
            val userString = username.text.toString()
            val passwordString = password.text.toString()
            intent.putExtra("username",userString)
            intent.putExtra("password",passwordString)
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}