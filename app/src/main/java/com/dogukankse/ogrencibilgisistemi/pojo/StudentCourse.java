package com.dogukankse.ogrencibilgisistemi.pojo;

public class StudentCourse {
    private long id;
    private int studentId;
    private int courseId;
    private String studentName;
    private String studentSurname;
    private String courseName;
    private float note;

    public StudentCourse(long id, int studentId, int courseId, float note) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
        this.note = note;
    }

    public StudentCourse() {

    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }


    public int getCourseId() {
        return courseId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentSurname() {
        return studentSurname;
    }

    public void setStudentSurname(String studentSurname) {
        this.studentSurname = studentSurname;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public float getNote() {
        return note;
    }

    public void setNote(float note) {
        this.note = note;
    }
}
