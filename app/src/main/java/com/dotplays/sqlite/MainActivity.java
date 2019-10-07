package com.dotplays.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        MySQLiteHelper mySQLiteHelper = new MySQLiteHelper(MainActivity.this);

        SinhVien sinhVien = new SinhVien();
        sinhVien.id = new Random().nextInt();
        sinhVien.name = "Huy Nguyen";
        sinhVien.phone = "091.336.0468";

        mySQLiteHelper.themSinhVien(sinhVien);


        ArrayList<SinhVien> sinhViens = mySQLiteHelper.danhSachSinhVien();

        Log.e("SO SV",sinhViens.size() +"");
    }


}
