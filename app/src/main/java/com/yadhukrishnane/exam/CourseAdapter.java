package com.yadhukrishnane.exam;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import androidx.appcompat.widget.AppCompatTextView;

public class CourseAdapter extends BaseAdapter {

    private List<Course> courses;
    private LayoutInflater inflater;

    public CourseAdapter(Context context, List<Course> courses) {
        this.courses = courses;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return courses.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = inflater.inflate(R.layout.single_spinner_item, parent, false);
        AppCompatTextView tvCourseName = view.findViewById(R.id.tv_single_course_name);
        Course course = courses.get(position);
        tvCourseName.setText(course.getCourseName());
        return view;
    }

//    @Override
//    public View getDropDownView(int position, View convertView, ViewGroup parent) {
//        View view = inflater.inflate(R.layout.single_spinner_item, parent, false);
//        AppCompatTextView tvCourseName = view.findViewById(R.id.tv_single_course_name);
//        Course course = courses.get(position);
//        tvCourseName.setText(course.getCourseName());
//        return view;
//    }
}
