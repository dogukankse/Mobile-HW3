package com.dogukankse.ogrencibilgisistemi.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    //db data
    private static final String DB_NAME = "StudentDataDB.DB";
    private static final int DB_VERSION = 1;

    //student table
    public static final String STUDENT_TABLE_NAME = "Students";
    public static final String STUDENT_ID = "id";
    public static final String STUDENT_NAME = "name";
    public static final String STUDENT_SURNAME = "surname";

    private static final String CREATE_STUDNET_TABLE = "create table " + STUDENT_TABLE_NAME +
            "(" + STUDENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            STUDENT_NAME + " TEXT NOT NULL, " +
            STUDENT_SURNAME + " TEXT NOT NULL);";

    //course table
    public static final String COURSE_TABLE_NAME = "Courses";
    public static final String COURSE_ID = "id";
    public static final String COURSE_NAME = "name";

    private static final String CREATE_COURSE_TABLE = "create table " + COURSE_TABLE_NAME +
            "(" + COURSE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COURSE_NAME + " TEXT NOT NULL);";


    //student-courses table
    public static final String STUDENT_COURSE_TABLE_NAME = "StudentCourses";
    public static final String STUDENT_COURSE_ID = "id";
    public static final String STUDENT_COURSE_STUDENT_ID = "student_id";
    public static final String STUDENT_COURSE_COURSE_ID = "course_id";

    private static final String CREATE_STUDENT_COURSE_TABLE = "create table " + STUDENT_COURSE_TABLE_NAME +
            "(" + STUDENT_COURSE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            STUDENT_COURSE_STUDENT_ID + " INTEGER NOT NULL, " +
            STUDENT_COURSE_COURSE_ID + " INTEGER NOT NULL," +
            "FOREIGN KEY (" + STUDENT_COURSE_STUDENT_ID + ") REFERENCES " + STUDENT_TABLE_NAME + " (" + STUDENT_ID + "));";

    //student-course-notes table
    public static final String STUDENT_COURSE_NOTES_TABLE_NAME = "StudentCourseNotes";
    public static final String STUDENT_COURSE_NOTES_ID = "id";
    public static final String STUDENT_COURSE_NOTES_STUDENT_ID = "student_id";
    public static final String STUDENT_COURSE_NOTES_COURSE_ID = "course_id";
    public static final String STUDENT_COURSE_NOTES_NOTE = "note";

    private static final String CREATE_STUDENT_COURSE_NOTES_TABLE = "create table " + STUDENT_COURSE_NOTES_TABLE_NAME +
            "(" + STUDENT_COURSE_NOTES_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            STUDENT_COURSE_NOTES_STUDENT_ID + " INTEGER NOT NULL, " +
            STUDENT_COURSE_NOTES_COURSE_ID + " INTEGER NOT NULL," +
            STUDENT_COURSE_NOTES_NOTE + " INTEGER NOT NULL," +
            "FOREIGN KEY (" + STUDENT_COURSE_NOTES_STUDENT_ID + ") REFERENCES " + STUDENT_TABLE_NAME + " (" + STUDENT_ID + ")," +
            "FOREIGN KEY (" + STUDENT_COURSE_NOTES_COURSE_ID + ") REFERENCES " + COURSE_TABLE_NAME + " (" + COURSE_ID + "));";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_STUDNET_TABLE);
        db.execSQL(CREATE_COURSE_TABLE);
        db.execSQL(CREATE_STUDENT_COURSE_TABLE);
        db.execSQL(CREATE_STUDENT_COURSE_NOTES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + STUDENT_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + COURSE_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + STUDENT_COURSE_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + STUDENT_COURSE_NOTES_TABLE_NAME);
        onCreate(db);
    }
}
