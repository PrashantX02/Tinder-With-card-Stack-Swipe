package com.example.tinder

import android.app.ActionBar.LayoutParams
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()

        val handler = Handler(Looper.getMainLooper())
        val intent = Intent(applicationContext,Home::class.java)
        val intent_2 = Intent(applicationContext,signUpandRegistration::class.java)

        if(auth.currentUser != null) {
            handler.postDelayed({
                startActivity(intent)
                finish()
            }, 2000)
        }else{
            handler.postDelayed({
                startActivity(intent_2)
                finish()
            }, 2000)
        }

    }
}