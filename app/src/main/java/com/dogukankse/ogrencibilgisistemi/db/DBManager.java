package com.dogukankse.ogrencibilgisistemi.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.dogukankse.ogrencibilgisistemi.pojo.*;

import java.util.ArrayList;

public class DBManager {

    private DatabaseHelper dbHelper;
    private Context context;
    private SQLiteDatabase db;

    public DBManager(Context context) {
        this.context = context;
    }

    public DBManager Open() {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
        Log.i("DB",db.getPath());
        return this;
    }

    public void Close() {
        dbHelper.close();
    }


    //student
    public void InsertStudent(String name, String surname) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.STUDENT_NAME, name);
        contentValue.put(DatabaseHelper.STUDENT_SURNAME, surname);
        db.insert(DatabaseHelper.STUDENT_TABLE_NAME, null, contentValue);
    }

    public int UpdateStudent(long id, String name, String surname) {
        ContentValues contentValue = new ContentValues();

        contentValue.put(DatabaseHelper.STUDENT_NAME, name);
        contentValue.put(DatabaseHelper.STUDENT_SURNAME, surname);

        return db.update(DatabaseHelper.STUDENT_TABLE_NAME, contentValue, DatabaseHelper.STUDENT_ID + "=" + id, null);
    }

    public void DeleteStudent(long id) {
        db.delete(DatabaseHelper.STUDENT_TABLE_NAME, DatabaseHelper.STUDENT_ID + "=" + id, null);
    }

    public ArrayList<Student> GetStudents() {
        ArrayList<Student> students = new ArrayList<>();
        String query = "SELECT * FROM " + DatabaseHelper.STUDENT_TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            students.add(new Student(
                    cursor.getInt(cursor.getColumnIndex(DatabaseHelper.STUDENT_ID)),
                    cursor.getString(cursor.getColumnIndex(DatabaseHelper.STUDENT_NAME)),
                    cursor.getString(cursor.getColumnIndex(DatabaseHelper.STUDENT_SURNAME))
            ));
        }
        cursor.close();
        return students;
    }

    //course
    public void InsertCourse(String name) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.COURSE_NAME, name);
        db.insert(DatabaseHelper.COURSE_TABLE_NAME, null, contentValue);
    }

    public void UpdateCourse(long id, String name) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.COURSE_NAME, name);
        db.update(DatabaseHelper.COURSE_TABLE_NAME, contentValue, DatabaseHelper.COURSE_ID + "=" + id, null);
    }

    public void DeleteCourse(long id) {
        db.delete(DatabaseHelper.COURSE_TABLE_NAME, DatabaseHelper.COURSE_ID + "=" + id, null);
    }

    public ArrayList<Course> GetCourses() {
        ArrayList<Course> courses= new ArrayList<>();
        String query = "SELECT * FROM " + DatabaseHelper.COURSE_TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            courses.add(new Course(
                    cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COURSE_ID)),
                    cursor.getString(cursor.getColumnIndex(DatabaseHelper.COURSE_NAME))
            ));
        }
        return courses;
    }

    //student-course
    public void InsertStudentCourse(long studentId, long courseId) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.STUDENT_COURSE_STUDENT_ID, studentId);
        contentValue.put(DatabaseHelper.STUDENT_COURSE_COURSE_ID, courseId);
        db.insert(DatabaseHelper.STUDENT_COURSE_TABLE_NAME, null, contentValue);
    }


    public void UpdateStudentCourse(long id, long studentId, long courseId) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.STUDENT_COURSE_STUDENT_ID, studentId);
        contentValue.put(DatabaseHelper.STUDENT_COURSE_COURSE_ID, courseId);
        db.update(DatabaseHelper.STUDENT_COURSE_TABLE_NAME, contentValue, DatabaseHelper.STUDENT_COURSE_ID + "=" + id, null);
    }

    public void DeleteStudentCourse(long id) {
        db.delete(DatabaseHelper.STUDENT_COURSE_TABLE_NAME, DatabaseHelper.STUDENT_COURSE_ID + "=" + id, null);
    }


    //student-course-note
    public void InsertStudentCourseNote(long studentId, long courseId, float note) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.STUDENT_COURSE_NOTES_STUDENT_ID, studentId);
        contentValue.put(DatabaseHelper.STUDENT_COURSE_NOTES_COURSE_ID, courseId);
        contentValue.put(DatabaseHelper.STUDENT_COURSE_NOTES_NOTE, note);
        db.insert(DatabaseHelper.STUDENT_COURSE_NOTES_TABLE_NAME, null, contentValue);
    }

    public void UpdateStudentCourseNote(long id, long studentId, long courseId, float note) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.STUDENT_COURSE_NOTES_STUDENT_ID, studentId);
        contentValue.put(DatabaseHelper.STUDENT_COURSE_NOTES_COURSE_ID, courseId);
        contentValue.put(DatabaseHelper.STUDENT_COURSE_NOTES_NOTE, note);
        db.update(DatabaseHelper.STUDENT_COURSE_NOTES_TABLE_NAME, contentValue, DatabaseHelper.STUDENT_COURSE_NOTES_ID + "=" + id, null);
    }

    public void DeleteStudentCourseNote(long id) {
        db.delete(DatabaseHelper.STUDENT_COURSE_NOTES_TABLE_NAME, DatabaseHelper.STUDENT_COURSE_NOTES_ID + "=" + id, null);
    }
}