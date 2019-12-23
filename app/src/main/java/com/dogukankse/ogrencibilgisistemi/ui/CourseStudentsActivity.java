package com.dogukankse.ogrencibilgisistemi.ui;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.dogukankse.ogrencibilgisistemi.R;
import com.dogukankse.ogrencibilgisistemi.adapters.StudentCourseListAdapter;
import com.dogukankse.ogrencibilgisistemi.db.DBManager;
import com.dogukankse.ogrencibilgisistemi.pojo.StudentCourse;

import java.util.ArrayList;

public class CourseStudentsActivity extends AppCompatActivity {
    long courseId;
    DBManager dbManager;
    ArrayList<StudentCourse> courseStudents;
    ListView courseStudentsListView;
    StudentCourseListAdapter courseStudentsListAdapter;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_students);
        Bundle b = getIntent().getExtras();
        courseId = b.getLong("id");

        fab = findViewById(R.id.fab_course_students);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CourseStudentsActivity.this, AddStudentToCourseActivity.class);
                intent.putExtra("courseId",courseId);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        dbManager = new DBManager(CourseStudentsActivity.this);
        dbManager.Open();
        courseStudents = dbManager.GetCourseStudents(courseId);


        courseStudentsListView = findViewById(R.id.course_students_list_view);
        courseStudentsListAdapter = new StudentCourseListAdapter(CourseStudentsActivity.this, courseStudents, true);
        courseStudentsListView.setAdapter(courseStudentsListAdapter);
        dbManager.Close();
    }
}
