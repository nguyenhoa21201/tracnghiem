package com.example.tracnghiem;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    private EditText emailedit, passwordedit;
    private Button btnlogin, btnregister;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle saveInstanceState)
    {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_login);
        mAuth= FirebaseAuth.getInstance();

        emailedit=findViewById(R.id.email);
        passwordedit=findViewById(R.id.password);
        btnlogin=findViewById(R.id.btnlogin);
        btnregister=findViewById(R.id.btnregister);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
    }

    private void register() {
        Intent i = new Intent(Login.this, Register.class);
        startActivity(i);
    }

    private void login() {
        String email, pass;
        email = emailedit.getText().toString();
        pass = passwordedit.getText().toString();
        if(TextUtils.isEmpty(email))
        {
            Toast.makeText(this, "Vui lòng nhập email.", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(pass))
        {
            Toast.makeText(this, "Vui lòng nhập password.", Toast.LENGTH_SHORT).show();
            return;
        }
        mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(getApplicationContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(Login.this,MainActivity.class);
                    startActivity(intent);
                }else
                {
                    Toast.makeText(getApplicationContext(), "Đăng  không thành công", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}
