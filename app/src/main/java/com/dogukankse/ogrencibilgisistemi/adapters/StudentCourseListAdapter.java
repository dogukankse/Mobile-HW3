package com.dogukankse.ogrencibilgisistemi.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dogukankse.ogrencibilgisistemi.R;
import com.dogukankse.ogrencibilgisistemi.pojo.StudentCourse;

import java.util.ArrayList;

public class StudentCourseListAdapter extends ArrayAdapter<StudentCourse> {

    private final LayoutInflater inflater;
    private final Context context;
    private ViewHolder holder;
    private final ArrayList<StudentCourse> studentCourses;

    public StudentCourseListAdapter(Context context, ArrayList<StudentCourse> studentCourses) {
        super(context, 0, studentCourses);
        this.context = context;
        this.studentCourses = studentCourses;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return studentCourses.size();
    }

    @Override
    public StudentCourse getItem(int position) {
        return studentCourses.get(position);
    }

    @Override
    public long getItemId(int position) {
        return studentCourses.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.student_courses_row, null);

            holder = new ViewHolder();
            holder.name = convertView.findViewById(R.id.student_course_list_name);
            holder.note = convertView.findViewById(R.id.student_course_list_note);

            convertView.setTag(holder);

        } else
            holder = (ViewHolder) convertView.getTag();

        StudentCourse studentCourse = getItem(position);
        if (studentCourse != null) {
            holder.name.setText(studentCourse.getCourseName());
            holder.note.setText(studentCourse.getNote() + "");
        }
        return convertView;
    }

    private static class ViewHolder {
        TextView name;
        TextView note;

    }
}
