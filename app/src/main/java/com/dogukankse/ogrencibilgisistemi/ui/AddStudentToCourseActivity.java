package com.dogukankse.ogrencibilgisistemi.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.dogukankse.ogrencibilgisistemi.R;
import com.dogukankse.ogrencibilgisistemi.db.DBManager;
import com.dogukankse.ogrencibilgisistemi.pojo.Student;

import java.util.ArrayList;

public class AddStudentToCourseActivity extends AppCompatActivity {

    long courseId;
    String item;

    Spinner spinner;
    EditText note;
    Button button;

    DBManager dbManager;
    ArrayList<Student> students;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student_to_course);
        Bundle b = getIntent().getExtras();
        courseId = b.getLong("courseId");

        dbManager = new DBManager(AddStudentToCourseActivity.this);
        dbManager.Open();

        spinner = findViewById(R.id.student_spinner);
        note = findViewById(R.id.add_note);
        button = findViewById(R.id.add_student_button);

        students = dbManager.GetOtherStudents(courseId);


        ArrayAdapter<Student> arrayAdapter = new ArrayAdapter<Student>(this, android.R.layout.simple_spinner_item, students);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!note.getText().toString().equals("")) {
                    Student s = (Student) spinner.getSelectedItem();
                    dbManager.InsertStudentCourse(s.getId(), courseId, Float.parseFloat(note.getText().toString()));
                    dbManager.Close();
                    finish();
                }
            }
        });

    }
}
