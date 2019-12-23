package com.dogukankse.ogrencibilgisistemi.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.dogukankse.ogrencibilgisistemi.pojo.Course;
import com.dogukankse.ogrencibilgisistemi.pojo.Student;
import com.dogukankse.ogrencibilgisistemi.pojo.StudentCourse;

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
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
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
        ArrayList<Course> courses = new ArrayList<>();
        String query = "SELECT * FROM " + DatabaseHelper.COURSE_TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            courses.add(new Course(
                    cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COURSE_ID)),
                    cursor.getString(cursor.getColumnIndex(DatabaseHelper.COURSE_NAME))
            ));
        }
        return courses;
    }

    //student-course
    public void InsertStudentCourse(long studentId, long courseId,float note) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.STUDENT_COURSE_STUDENT_ID, studentId);
        contentValue.put(DatabaseHelper.STUDENT_COURSE_COURSE_ID, courseId);
        contentValue.put(DatabaseHelper.STUDENT_COURSE_NOTE,note);
        db.insert(DatabaseHelper.STUDENT_COURSE_TABLE_NAME, null, contentValue);
    }


    public void UpdateStudentCourse(long id, float note) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.STUDENT_COURSE_NOTE, note);
        db.update(DatabaseHelper.STUDENT_COURSE_TABLE_NAME, contentValue,
                DatabaseHelper.STUDENT_COURSE_ID + " = " + id, null);
    }

    public void DeleteStudentCourse(long id) {
        db.delete(DatabaseHelper.STUDENT_COURSE_TABLE_NAME, DatabaseHelper.STUDENT_COURSE_ID + "=" + id, null);
    }

    public ArrayList<StudentCourse> GetStudentCourses(long studentId) {
        ArrayList<StudentCourse> studentCourses = new ArrayList<>();
        String query = "SELECT * " +
                "FROM StudentCourses " +
                "WHERE student_id = " + studentId;
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {

            StudentCourse item = new StudentCourse(
                    cursor.getInt(cursor.getColumnIndex(DatabaseHelper.STUDENT_COURSE_ID)),
                    cursor.getInt(cursor.getColumnIndex(DatabaseHelper.STUDENT_COURSE_STUDENT_ID)),
                    cursor.getInt(cursor.getColumnIndex(DatabaseHelper.STUDENT_COURSE_COURSE_ID)),
                    cursor.getFloat(cursor.getColumnIndex(DatabaseHelper.STUDENT_COURSE_NOTE))
            );


            String localQuery = "SELECT  name " +
                    "FROM Courses " +
                    "INNER JOIN StudentCourses " +
                    "ON Courses.id = StudentCourses.course_id " +
                    "WHERE " + item.getId() + " = StudentCourses.id";
            Cursor localCursor = db.rawQuery(localQuery, null);

            while (localCursor.moveToNext()) {
                item.setCourseName(localCursor.getString(localCursor.getColumnIndex(DatabaseHelper.COURSE_NAME)));
            }
            if (item.getCourseName() != null)
                studentCourses.add(item);

        }


        return studentCourses;
    }


    public ArrayList<StudentCourse> GetCourseStudents(long courseId) {
        ArrayList<StudentCourse> courseStudents = new ArrayList<>();
        String query = "SELECT * " +
                "FROM StudentCourses " +
                "WHERE course_id = " + courseId;
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {

            StudentCourse item = new StudentCourse(
                    cursor.getInt(cursor.getColumnIndex(DatabaseHelper.STUDENT_COURSE_ID)),
                    cursor.getInt(cursor.getColumnIndex(DatabaseHelper.STUDENT_COURSE_STUDENT_ID)),
                    cursor.getInt(cursor.getColumnIndex(DatabaseHelper.STUDENT_COURSE_COURSE_ID)),
                    cursor.getFloat(cursor.getColumnIndex(DatabaseHelper.STUDENT_COURSE_NOTE)));


            String localQuery = "SELECT  name,surname " +
                    "FROM Students " +
                    "INNER JOIN StudentCourses " +
                    "ON StudentCourses.student_id = Students.id " +
                    "WHERE " + item.getCourseId() + " = StudentCourses.course_id " +
                    "AND " + item.getStudentId() + " = StudentCourses.student_id";
            Cursor localCursor = db.rawQuery(localQuery, null);

            while (localCursor.moveToNext()) {
                item.setStudentName(localCursor.getString(localCursor.getColumnIndex(DatabaseHelper.STUDENT_NAME)));
                item.setStudentSurname(localCursor.getString(localCursor.getColumnIndex(DatabaseHelper.STUDENT_SURNAME)));
            }
            if (item.getStudentName() != null)
                courseStudents.add(item);

        }


        return courseStudents;
    }

    public StudentCourse GetStudentCourse(long studentCourseId) {
        StudentCourse studentCourse = new StudentCourse();
        String query = "SELECT " + DatabaseHelper.STUDENT_COURSE_NOTE + ", " + DatabaseHelper.COURSE_NAME +
                " From " + DatabaseHelper.STUDENT_COURSE_TABLE_NAME +
                " INNER JOIN " + DatabaseHelper.COURSE_TABLE_NAME +
                " ON " + DatabaseHelper.STUDENT_COURSE_TABLE_NAME + "." + DatabaseHelper.STUDENT_COURSE_COURSE_ID
                + " = " + DatabaseHelper.COURSE_TABLE_NAME + "." + DatabaseHelper.COURSE_ID +
                " WHERE " + studentCourseId + " = " + DatabaseHelper.STUDENT_COURSE_TABLE_NAME + "." + DatabaseHelper.STUDENT_COURSE_ID;
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            studentCourse.setNote(cursor.getFloat(cursor.getColumnIndex(DatabaseHelper.STUDENT_COURSE_NOTE)));
            studentCourse.setCourseName(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COURSE_NAME)));
            studentCourse.setId((int) studentCourseId);
        }
        return studentCourse;
    }

    public ArrayList<Student> GetOtherStudents(long courseId) {
        ArrayList<Student> students = new ArrayList<>();

        String query = "select DISTINCT name,surname,student_id " +
                "from Students " +
                "inner join StudentCourses " +
                "on Students.id = StudentCourses.student_id " +
                "where " + courseId + " != StudentCourses.course_id " +
                "EXCEPT " +
                "select name,surname,student_id " +
                "from Students " +
                "inner join StudentCourses " +
                "on Students.id = StudentCourses.student_id " +
                "where " + courseId + " = StudentCourses.course_id";

        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext()) {
            students.add(new Student(
                    cursor.getInt(cursor.getColumnIndex(DatabaseHelper.STUDENT_COURSE_STUDENT_ID)),
                    cursor.getString(cursor.getColumnIndex(DatabaseHelper.STUDENT_NAME)),
                    cursor.getString(cursor.getColumnIndex(DatabaseHelper.STUDENT_SURNAME))));
        }
        return students;
    }
}
