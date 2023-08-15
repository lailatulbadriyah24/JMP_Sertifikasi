package com.example.jmp_sertifikasi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText txEmail, txPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txEmail = findViewById(R.id.txEmail);
        txPassword = findViewById(R.id.txPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = txEmail.getText().toString();
                String password = txPassword.getText().toString();

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Isi email dan password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (email.equals("lailatulbadriyah654@gmail.com") && password.equals("laila123")) {
                    Intent intent = new Intent(LoginActivity.this, LoginSuccessActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Email atau password salah", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}