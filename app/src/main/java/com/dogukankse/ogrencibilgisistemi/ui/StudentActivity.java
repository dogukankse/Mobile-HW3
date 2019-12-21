package com.dogukankse.ogrencibilgisistemi.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.dogukankse.ogrencibilgisistemi.R;
import com.dogukankse.ogrencibilgisistemi.db.DBManager;

public class StudentActivity extends AppCompatActivity {
    DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        dbManager = new DBManager(this);
        dbManager.Open();
        Log.i("DB", dbManager.toString());
    }
}
