package com.dogukankse.ogrencibilgisistemi.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.dogukankse.ogrencibilgisistemi.R;
import com.dogukankse.ogrencibilgisistemi.adapters.StudentCourseListAdapter;
import com.dogukankse.ogrencibilgisistemi.db.DBManager;
import com.dogukankse.ogrencibilgisistemi.pojo.StudentCourse;

public class NoteChangeActivity extends AppCompatActivity {

    long studentCourseId;
    DBManager dbManager;
    StudentCourse stundentCourseData;

    TextView courseName;
    EditText courseNote;
    Button courseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_change);
        Bundle b = getIntent().getExtras();
        studentCourseId = b.getLong("id");

        courseName = findViewById(R.id.note_change_course_name);
        courseNote = findViewById(R.id.note_change_note);
        courseButton = findViewById(R.id.note_change_button);


        dbManager = new DBManager(NoteChangeActivity.this);
        dbManager.Open();
        stundentCourseData = dbManager.GetStudentCourse(studentCourseId);

        courseName.setText(stundentCourseData.getCourseName());
        courseNote.setText(stundentCourseData.getNote() + "");

        courseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!courseNote.getText().toString().equals("")) {
                    dbManager.UpdateStudentCourse(studentCourseId, Float.parseFloat(courseNote.getText().toString()));
                    dbManager.Close();
                    finish();
                }
            }
        });




    }
}
