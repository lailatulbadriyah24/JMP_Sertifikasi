package com.example.jmp_sertifikasi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ImageButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class UpdateActivity extends AppCompatActivity {

    protected Cursor cursor;
    DBHelper database;
    Button btn_simpan;
    EditText nomor, nama, tanggal, jk, alamat;
    ImageButton actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        actionBar = findViewById(R.id.backButton);
        database = new DBHelper(this);
        nomor = findViewById(R.id.nomor);
        nama = findViewById(R.id.nama);
        tanggal = findViewById(R.id.tanggal);
        jk = findViewById(R.id.jk);
        alamat = findViewById(R.id.alamat);
        btn_simpan = findViewById(R.id.buttonsimpan);

        tanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });

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
        }

        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = database.getWritableDatabase();
                db.execSQL("UPDATE mahasiswa SET nomor = '"+
                        nomor.getText().toString() + "', nama = '" +
                        nama.getText().toString() + "', tanggal = '" +
                        tanggal.getText().toString() + "', jk = '" +
                        jk.getText().toString() + "', alamat = '" +
                        alamat.getText().toString() + "' WHERE nama = '"+
                        getIntent().getStringExtra("nama")+"'");
                Toast.makeText(UpdateActivity.this, "Data berhasil diupdate", Toast.LENGTH_SHORT).show();
                ListDataActivity.ma.RefreshList();
                finish();
            }
        });
        actionBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    private void showDatePicker() {
        final Calendar myCalendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String myFormat = "dd/MM/yy"; // Format tanggal yang diinginkan
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                tanggal.setText(sdf.format(myCalendar.getTime()));
            }
        };

        new DatePickerDialog(UpdateActivity.this, date,
                myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }
}