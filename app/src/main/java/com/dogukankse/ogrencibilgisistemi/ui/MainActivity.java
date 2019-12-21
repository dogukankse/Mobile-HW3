package com.dogukankse.ogrencibilgisistemi.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.dogukankse.ogrencibilgisistemi.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button students_button = findViewById(R.id.stundest_button);
        Button lectures_button = findViewById(R.id.lectures_button);

        students_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), StudentActivity.class);
                startActivity(intent);
            }
        });

        lectures_button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(),LecturesActivity.class);
                startActivity(intent);
            }
        });
    }
}
