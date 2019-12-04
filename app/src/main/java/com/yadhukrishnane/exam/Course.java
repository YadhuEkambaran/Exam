package com.yadhukrishnane.exam;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private String courseName;
    private int fees;
    private int hours;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getFees() {
        return fees;
    }

    public void setFees(int fees) {
        this.fees = fees;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public static List<Course> getCourses() {
        List<Course> courses = new ArrayList<>();

        Course course0 = new Course();
        course0.setCourseName("Select a course");
        course0.setFees(0);
        course0.setHours(0);

        Course course = new Course();
        course.setCourseName("Java");
        course.setFees(1300);
        course.setHours(6);

        Course course1 = new Course();
        course1.setCourseName("Swift");
        course1.setFees(1500);
        course1.setHours(5);

        Course course2 = new Course();
        course2.setCourseName("iOs");
        course2.setFees(1350);
        course2.setHours(5);

        Course course3 = new Course();
        course3.setCourseName("Android");
        course3.setFees(1400);
        course3.setHours(7);

        Course course4 = new Course();
        course4.setCourseName("Database");
        course4.setFees(1000);
        course4.setHours(4);

        courses.add(course0);
        courses.add(course);
        courses.add(course1);
        courses.add(course2);
        courses.add(course3);
        courses.add(course4);

        return courses;
    }
}
