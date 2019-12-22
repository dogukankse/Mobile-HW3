package com.dogukankse.ogrencibilgisistemi.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.dogukankse.ogrencibilgisistemi.R;
import com.dogukankse.ogrencibilgisistemi.adapters.CourseListAdapter;
import com.dogukankse.ogrencibilgisistemi.db.DBManager;
import com.dogukankse.ogrencibilgisistemi.pojo.Course;

import java.util.ArrayList;

public class CourseActivity extends AppCompatActivity {

    DBManager dbManager;
    ArrayList<Course> courses;
    ListView courseListView;
    CourseListAdapter courseListAdapter;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);
    }

    @Override
    protected void onResume() {
        super.onResume();

        dbManager = new DBManager(this);
        dbManager.Open();
        courses = dbManager.GetCourses();

        courseListView = findViewById(R.id.course_list_view);
        courseListAdapter = new CourseListAdapter(CourseActivity.this, courses);
        courseListView.setAdapter(courseListAdapter);
        dbManager.Close();

        fab = findViewById(R.id.fab_course);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CourseActivity.this, AddCourseActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbManager.Close();
    }
}
