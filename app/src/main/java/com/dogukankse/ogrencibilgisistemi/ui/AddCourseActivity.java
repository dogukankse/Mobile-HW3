package com.dogukankse.ogrencibilgisistemi.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.dogukankse.ogrencibilgisistemi.R;
import com.dogukankse.ogrencibilgisistemi.db.DBManager;

public class AddCourseActivity extends AppCompatActivity {

    EditText name;
    Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        name = findViewById(R.id.course_name_edit);
        addButton = findViewById(R.id.course_add_button);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String courseName = name.getText().toString();

                if (!courseName.equals("")) {
                    DBManager dbManager = new DBManager(AddCourseActivity.this);
                    dbManager.Open();
                    dbManager.InsertCourse(courseName);
                    dbManager.Close();

                    finish();
                }
            }
        });

    }
}
