package com.yadhukrishnane.exam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.AppCompatTextView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;

public class SecondActivity extends AppCompatActivity {

    public static final String BUNDLE_STUDENT_NAME = "STUDENT_NAME";
    public static final String WELCOME_TEMPLATE = "Welcome %s";
    public static final String FEE_TEMPLATE = "Fees :  $%d";
    public static final String HOURS_TEMPLATE = "Hours : $%d";
    public static final String TOTAL_FEE_TEMPLATE = "Total Fees :  $%d";
    public static final String TOTAL_HOURS_TEMPLATE = "Total Hours : $%d";

    private AppCompatTextView tvWelcome;
    private RadioGroup rgQuaification;
    private AppCompatTextView tvFees;
    private AppCompatTextView tvHourse;
    private AppCompatTextView tvTotalFees;
    private AppCompatTextView tvTotalHours;
    private AppCompatCheckBox cbAccommodation;
    private AppCompatCheckBox cbInsurance;
    private AppCompatSpinner spCourse;

    private int maxHours = 0;
    private int totalFees;
    private int totalHours;
    private List<Course> courses;
    private Course currentSelectedCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        tvWelcome = findViewById(R.id.tv_second_welcome);
        rgQuaification = findViewById(R.id.rg_qualification);
        spCourse = findViewById(R.id.sp_second_course);
        tvFees = findViewById(R.id.tv_second_course_fee);
        tvHourse = findViewById(R.id.tv_second_course_hours);
        tvTotalFees = findViewById(R.id.tv_second_total_fees);
        tvTotalHours = findViewById(R.id.tv_second_total_hours);
        cbAccommodation = findViewById(R.id.cb_second_accommodation);
        cbInsurance = findViewById(R.id.cb_second_medical_insurance);
        AppCompatButton btnAdd = findViewById(R.id.btn_second_add_course);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentSelectedCourse == null) {
                    Toast.makeText(SecondActivity.this, "Select a course", Toast.LENGTH_SHORT).show();
                    return;
                }

                setTotalFeesAndHours();
            }
        });

        setWelcome();
        selectQualification();
        spinnerSelection();
        setAccoAndInsurance();
    }

    private void setWelcome() {
        String name = getIntent().getStringExtra(BUNDLE_STUDENT_NAME);
        tvWelcome.setText(String.format(Locale.getDefault(), WELCOME_TEMPLATE, name));
    }

    private void selectQualification() {
        rgQuaification.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.rb_graduated) {
                    maxHours = 21;
                } else {
                    maxHours = 19;
                }
            }
        });
    }

    private void spinnerSelection() {
        courses = Course.getCourses();
        CourseAdapter adapter = new CourseAdapter(SecondActivity.this, courses);
        spCourse.setAdapter(adapter);

        spCourse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (maxHours == 0) {
                    spCourse.setSelection(0);
                    Toast.makeText(SecondActivity.this, "Please select qualification", Toast.LENGTH_SHORT).show();
                    return;
                }

                currentSelectedCourse = courses.get(i);
                setCourseFeeAndHours();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void setCourseFeeAndHours() {
        tvFees.setText(String.format(Locale.getDefault(), FEE_TEMPLATE, currentSelectedCourse.getFees()));
        tvHourse.setText(String.format(Locale.getDefault(), HOURS_TEMPLATE, currentSelectedCourse.getHours()));
    }

    private void setTotalFeesAndHours() {
        int tempHours = totalHours + currentSelectedCourse.getHours();
        if (tempHours > maxHours) {
            Toast.makeText(SecondActivity.this, "you can’t add this course", Toast.LENGTH_SHORT).show();
            return;
        }

        totalFees += currentSelectedCourse.getFees();
        totalHours = tempHours;

        tvTotalHours.setText(String.format(Locale.getDefault(), TOTAL_HOURS_TEMPLATE, totalHours));
        setTotalFees();

        currentSelectedCourse = null;
    }

    private void setAccoAndInsurance() {
        cbAccommodation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    totalFees += 1000;
                } else {
                    totalFees -= 1000;
                }

                setTotalFees();
            }
        });

        cbInsurance.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    totalFees += 700;
                } else {
                    totalFees -= 700;
                }

                setTotalFees();
            }
        });
    }

    private void setTotalFees() {
        tvTotalFees.setText(String.format(Locale.getDefault(), TOTAL_FEE_TEMPLATE, totalFees));
    }
}
