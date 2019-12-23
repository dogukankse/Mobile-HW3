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
import com.dogukankse.ogrencibilgisistemi.pojo.Student;
import com.dogukankse.ogrencibilgisistemi.ui.StudentCoursesActivity;

import java.util.ArrayList;

public class StudentListAdapter extends ArrayAdapter<Student> {
    private final LayoutInflater inflater;
    private final Context context;
    private ViewHolder holder;
    private final ArrayList<Student> students;
    private DBManager dbManager;

    public StudentListAdapter(Context context, ArrayList<Student> students) {
        super(context, 0, students);
        this.context = context;
        this.students = students;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return students.size();
    }

    @Override
    public Student getItem(int position) {
        return students.get(position);
    }

    @Override
    public long getItemId(int position) {
        return students.get(position).getId();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.student_list_row, null);

            holder = new ViewHolder();
            holder.name = convertView.findViewById(R.id.student_name);
            holder.surname = convertView.findViewById(R.id.student_surname);
            holder.id = convertView.findViewById(R.id.student_id);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Student student = getItem(position);
        if (student != null) {
            holder.name.setText(student.getName());
            holder.surname.setText(student.getSurname());
            holder.id.setText("" + student.getId());
        }

        convertView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                dbManager = new DBManager(context);
                dbManager.Open();
                Log.i("LONGPRESS", getItemId(position) + "");
                dbManager.DeleteStudent(getItemId(position));
                students.remove(position);
                notifyDataSetChanged();
                dbManager.Close();
                return true;
            }
        });

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, StudentCoursesActivity.class);
                intent.putExtra("id",getItemId(position));
                context.startActivity(intent);
            }
        });

        return convertView;

    }

    private static class ViewHolder {
        TextView name;
        TextView surname;
        TextView id;
    }
}
