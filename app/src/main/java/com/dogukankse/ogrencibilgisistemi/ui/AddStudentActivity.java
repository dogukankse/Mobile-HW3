package com.dogukankse.ogrencibilgisistemi.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.dogukankse.ogrencibilgisistemi.R;
import com.dogukankse.ogrencibilgisistemi.db.DBManager;

public class AddStudentActivity extends AppCompatActivity {

    EditText name;
    EditText surname;
    Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        name = findViewById(R.id.student_name_edit);
        surname = findViewById(R.id.student_surname_edit);
        addButton = findViewById(R.id.student_add_button);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameStr = name.getText().toString();
                String surnameStr = surname.getText().toString();
                if (!nameStr.equals("") && !surnameStr.equals("")) {
                    DBManager dbManager = new DBManager(AddStudentActivity.this);
                    dbManager.Open();
                    dbManager.InsertStudent(nameStr, surnameStr);
                    dbManager.Close();

                    finish();
                }
            }
        });


    }
}
