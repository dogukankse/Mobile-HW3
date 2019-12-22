package com.dogukankse.ogrencibilgisistemi.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.dogukankse.ogrencibilgisistemi.R;
import com.dogukankse.ogrencibilgisistemi.db.DBManager;
import com.dogukankse.ogrencibilgisistemi.pojo.Student;

import java.util.ArrayList;

public class StudentActivity extends AppCompatActivity {

    DBManager dbManager;
    ArrayList<Student> students;
    ListView studentListView;
    StudentListAdapter studentListAdapter;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);


    }

    @Override
    protected void onResume() {
        super.onResume();

        dbManager = new DBManager(this);
        dbManager.Open();
        students = dbManager.GetStudents();

        studentListView = findViewById(R.id.student_list_view);
        studentListAdapter = new StudentListAdapter(StudentActivity.this, students);
        studentListView.setAdapter(studentListAdapter);
        dbManager.Close();


        fab = findViewById(R.id.fab_student);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentActivity.this, AddStudentActivity.class);
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

