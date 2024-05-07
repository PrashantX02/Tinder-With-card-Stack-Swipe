package com.example.tinder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class log_in_only extends AppCompatActivity {
    private EditText email;
    private EditText password;

    private TextView log_in;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_only);

        log_in = findViewById(R.id.textView12);
        email = findViewById(R.id.editText3);
        password = findViewById(R.id.editText2);

        auth = FirebaseAuth.getInstance();

        log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String em = email.getText().toString().trim();
                String ps = password.getText().toString().trim();

                if(em.isEmpty() || ps.isEmpty()){
                    Toast.makeText(getApplicationContext(),"incorrect credential",Toast.LENGTH_SHORT).show();
                }

                auth.signInWithEmailAndPassword(em,ps).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            if(auth.getCurrentUser().isEmailVerified()){
                                Intent intent = new Intent(log_in_only.this,Home.class);
                                startActivity(intent);
                                finish();
                            }else{
                                Toast.makeText(getApplicationContext(),"please verify email first",Toast.LENGTH_SHORT).show();
                            }

                        }else{
                            Toast.makeText(getApplicationContext(),"please verify email first",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }
}