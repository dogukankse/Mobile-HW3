package com.dogukankse.ogrencibilgisistemi.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dogukankse.ogrencibilgisistemi.R;
import com.dogukankse.ogrencibilgisistemi.db.DBManager;
import com.dogukankse.ogrencibilgisistemi.pojo.Course;
import com.dogukankse.ogrencibilgisistemi.ui.CourseStudentsActivity;

import java.util.ArrayList;

public class CourseListAdapter extends ArrayAdapter<Course> {
    private final LayoutInflater inflater;
    private final Context context;
    private ViewHolder holder;
    private final ArrayList<Course> courses;
    private DBManager dbManager;

    public CourseListAdapter(Context context, ArrayList<Course> courses) {
        super(context, 0, courses);
        this.context = context;
        this.courses = courses;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return courses.size();
    }

    @Override
    public Course getItem(int position) {
        return courses.get(position);
    }

    @Override
    public long getItemId(int position) {
        return courses.get(position).getId();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.student_list_row, null);

            holder = new ViewHolder();
            holder.name = convertView.findViewById(R.id.student_name);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Course course = getItem(position);
        if (course != null) {
            holder.name.setText(course.getName());

        }

        convertView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                dbManager = new DBManager(context);
                dbManager.Open();
                Log.i("LONGPRESS", getItemId(position) + "");
                dbManager.DeleteCourse(getItemId(position));
                courses.remove(position);
                notifyDataSetChanged();
                dbManager.Close();
                return true;
            }
        });

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CourseStudentsActivity.class);
                intent.putExtra("id", getItemId(position));
                context.startActivity(intent);
            }
        });

        return convertView;

    }

    private static class ViewHolder {
        TextView name;

    }
}
