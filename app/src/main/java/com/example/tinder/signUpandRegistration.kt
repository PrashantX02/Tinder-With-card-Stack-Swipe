package com.example.tinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView


class signUpandRegistration : AppCompatActivity() {


    lateinit var log: LinearLayout
    private lateinit var to_registration:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN
//               ,WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_sign_upand_registration)

        log = findViewById(R.id.log_in)
        to_registration = findViewById(R.id.Click_here)




        var intent = Intent(applicationContext,Registration_only::class.java)
        var to_log_in_page = Intent(applicationContext,log_in_only::class.java)

        to_registration.setOnClickListener{
            startActivity(intent)
        }

        log.setOnClickListener{
            startActivity(to_log_in_page)
        }
    }
}