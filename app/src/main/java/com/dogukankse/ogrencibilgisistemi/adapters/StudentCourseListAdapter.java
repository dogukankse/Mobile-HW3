package com.dogukankse.ogrencibilgisistemi.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dogukankse.ogrencibilgisistemi.R;
import com.dogukankse.ogrencibilgisistemi.pojo.StudentCourse;
import com.dogukankse.ogrencibilgisistemi.ui.NoteChangeActivity;

import java.util.ArrayList;

public class StudentCourseListAdapter extends ArrayAdapter<StudentCourse> {

    private final LayoutInflater inflater;
    private final Context context;
    private ViewHolder holder;
    private final ArrayList<StudentCourse> studentCourses;
    private Boolean forCourses;

    public StudentCourseListAdapter(Context context, ArrayList<StudentCourse> studentCourses, Boolean forCourses) {
        super(context, 0, studentCourses);
        this.context = context;
        this.studentCourses = studentCourses;
        inflater = LayoutInflater.from(context);
        this.forCourses = forCourses;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
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
            if (forCourses)
                holder.name.setText(studentCourse.getStudentName() + " " + studentCourse.getStudentSurname());
            else
                holder.name.setText(studentCourse.getCourseName());

            holder.note.setText(studentCourse.getNote() + "");
        }

        if(forCourses){
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, NoteChangeActivity.class);
                    intent.putExtra("id",getItemId(position));
                    context.startActivity(intent);
                }
            });
        }

        return convertView;
    }

    private static class ViewHolder {
        TextView name;
        TextView note;

    }
}
