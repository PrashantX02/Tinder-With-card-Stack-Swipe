package com.example.tinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthEmailException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.database.FirebaseDatabase
import java.util.concurrent.TimeoutException

class Verification_email : AppCompatActivity() {

    lateinit var email:EditText
    lateinit var verify: TextView
    lateinit var progressBar : ProgressBar
    lateinit var em:String
    lateinit var password:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        window.setFlags(
//            WindowManager.LayoutParams.FLAG_FULLSCREEN
//            , WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_verification_email)

        progressBar = findViewById(R.id.progressBar)

        val auth = FirebaseAuth.getInstance()
        val database = FirebaseDatabase.getInstance()

        verify = findViewById(R.id.textView4)

        progressBar.visibility = View.GONE

        val receivedIntent = getIntent()


        email = findViewById(R.id.editText)

        verify.setOnClickListener{
            em = email.text.toString().trim()
            val username = receivedIntent.getStringExtra("username").toString()
            password  = receivedIntent.getStringExtra("password").toString()


            //Toast.makeText(applicationContext, password, Toast.LENGTH_LONG).show()

            progressBar.visibility = View.VISIBLE

            auth.createUserWithEmailAndPassword(em,password).addOnCompleteListener{ task ->
                if(task.isSuccessful){
                    auth.currentUser?.sendEmailVerification()?.addOnCompleteListener { emailTask->
                        if(emailTask.isSuccessful){
                            Toast.makeText(applicationContext,"Verification email was sent!",Toast.LENGTH_LONG).show()
                            val intent = Intent(applicationContext,log_in_only::class.java)

                            val reference = database.getReference("user").child("name").push()
                            reference.setValue(username)
                            progressBar.visibility = View.GONE
                            startActivity(intent)
                            finish()
                        }else{
                            progressBar.visibility = View.GONE
                            Toast.makeText(applicationContext,"Unable to send verification email",Toast.LENGTH_LONG).show()
                        }
                    }
                }else{
                    progressBar.visibility = View.GONE
                    Toast.makeText(applicationContext,"Unable to create user: ${task.exception?.message}",Toast.LENGTH_LONG).show()
                }
            }.addOnFailureListener { exception ->
                // This block will handle the failure to create a user
                progressBar.visibility = View.GONE
                when (exception) {
                    is FirebaseAuthWeakPasswordException -> {
                        Toast.makeText(applicationContext, "Weak password", Toast.LENGTH_LONG).show()
                    }
                    is FirebaseAuthInvalidCredentialsException -> {
                        Toast.makeText(applicationContext, "Invalid email", Toast.LENGTH_LONG).show()
                    }
                    is FirebaseAuthUserCollisionException -> {
                        Toast.makeText(applicationContext, "User with this email already exists", Toast.LENGTH_LONG).show()
                    }
                    is FirebaseAuthEmailException -> {
                        Toast.makeText(applicationContext, "Email exception: ${exception.message}", Toast.LENGTH_LONG).show()
                    }
                    is TimeoutException -> {
                        Toast.makeText(applicationContext, "Operation timed out", Toast.LENGTH_LONG).show()
                    }
                    is InterruptedException -> {
                        Toast.makeText(applicationContext, "Operation interrupted", Toast.LENGTH_LONG).show()
                    }
                    else -> {
                        Toast.makeText(applicationContext, "Failed to create user: ${exception.message}", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }

    }
}