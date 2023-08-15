package com.example.jmp_sertifikasi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        TextView btnLihat = findViewById(R.id.btn_lihat);
        TextView btnTambah = findViewById(R.id.btn_tambah);
        TextView btnInfo = findViewById(R.id.btn_informasi);

        btnLihat.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent lihat = new Intent(DashboardActivity.this, ListDataActivity.class);
                startActivity(lihat);

            }
        });
        btnTambah.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent tambah = new Intent(DashboardActivity.this, CreateActivity.class);
                startActivity(tambah);

            }
        });
        btnInfo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent info = new Intent(DashboardActivity.this, InformasiActivity.class);
                startActivity(info);
            }
        });
    }
}