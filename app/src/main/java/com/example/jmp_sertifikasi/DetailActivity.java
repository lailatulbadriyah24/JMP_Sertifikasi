package com.example.jmp_sertifikasi;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    protected Cursor cursor;
    DBHelper database;
    TextView nomor, nama, tanggal, jk, alamat;
    ImageButton actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        actionBar = findViewById(R.id.backButton);
        database = new DBHelper(this);
        nomor = findViewById(R.id.nomor);
        nama = findViewById(R.id.nama);
        tanggal = findViewById(R.id.tanggal);
        jk = findViewById(R.id.jk);
        alamat = findViewById(R.id.alamat);

        SQLiteDatabase db = database.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM mahasiswa WHERE nama = '"+
                getIntent().getStringExtra("nama")+"'", null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            int columnIndexNomor = cursor.getColumnIndex("nomor");
            int columnIndexNama = cursor.getColumnIndex("nama");
            int columnIndexTanggal = cursor.getColumnIndex("tanggal");
            int columnIndexJk = cursor.getColumnIndex("jk");
            int columnIndexAlamat = cursor.getColumnIndex("alamat");

            if (columnIndexNomor >= 0) {
                nomor.setText(String.valueOf(cursor.getInt(columnIndexNomor)));
            }
            if (columnIndexNama >= 0) {
                nama.setText(cursor.getString(columnIndexNama));
            }
            if (columnIndexTanggal >= 0) {
                tanggal.setText(cursor.getString(columnIndexTanggal));
            }
            if (columnIndexJk >= 0) {
                jk.setText(cursor.getString(columnIndexJk));
            }
            if (columnIndexAlamat >= 0) {
                alamat.setText(cursor.getString(columnIndexAlamat));
            }
        } else {
            // Handle when there's no data found
        }
        cursor.close();
        actionBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}