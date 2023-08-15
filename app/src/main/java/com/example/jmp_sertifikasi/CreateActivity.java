package com.example.jmp_sertifikasi;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CreateActivity extends AppCompatActivity {

    protected Cursor cursor;
    DBHelper database;
    Button btn_simpan;
    EditText nomor, nama, tanggal, jk, alamat;
    ImageButton actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

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

        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = database.getWritableDatabase();
                db.execSQL("INSERT INTO mahasiswa(nomor, nama, tanggal, jk, alamat) VALUES('" +
                        nomor.getText().toString() + "','" +
                        nama.getText().toString() + "','" +
                        tanggal.getText().toString() + "','" +
                        jk.getText().toString() + "','" +
                        alamat.getText().toString() + "')");
                Toast.makeText(CreateActivity.this, "Data Tersimpan", Toast.LENGTH_SHORT).show();
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

        new DatePickerDialog(CreateActivity.this, date,
                myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }
}
