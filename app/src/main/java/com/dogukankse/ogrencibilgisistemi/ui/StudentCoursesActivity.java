package com.dogukankse.ogrencibilgisistemi.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.dogukankse.ogrencibilgisistemi.R;
import com.dogukankse.ogrencibilgisistemi.adapters.StudentCourseListAdapter;
import com.dogukankse.ogrencibilgisistemi.db.DBManager;
import com.dogukankse.ogrencibilgisistemi.pojo.StudentCourse;

import java.util.ArrayList;

public class StudentCoursesActivity extends AppCompatActivity {

    long studentId;
    DBManager dbManager;
    ArrayList<StudentCourse> studentCourses;
    ListView studentCourseListView;
    StudentCourseListAdapter studentCourseListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_courses);
        Bundle b = getIntent().getExtras();
        studentId = b.getLong("id");
        dbManager = new DBManager(StudentCoursesActivity.this);
        dbManager.Open();
        studentCourses = dbManager.GetStudentCourses(studentId);

        studentCourseListView = findViewById(R.id.student_courses_list_view);
        studentCourseListAdapter = new StudentCourseListAdapter(StudentCoursesActivity.this, studentCourses, false);
        studentCourseListView.setAdapter(studentCourseListAdapter);
        dbManager.Close();

    }
}
