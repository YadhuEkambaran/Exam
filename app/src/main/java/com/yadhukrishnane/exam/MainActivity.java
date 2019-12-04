package com.yadhukrishnane.exam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private AppCompatEditText edtUsername;
    private AppCompatEditText edtPassword;
    private AppCompatEditText edtStudentName;
    private AppCompatButton btnLogin;
    private AppCompatButton btnClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtUsername = findViewById(R.id.edt_login_username);
        edtPassword = findViewById(R.id.edt_login_password);
        edtStudentName = findViewById(R.id.edt_login_name);
        btnLogin = findViewById(R.id.btn_login);
        btnClear = findViewById(R.id.btn_clear);

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clear();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validation()) {
                    if (loginCheck()) {
                        goToNextPage();
                    } else {
                        Toast.makeText(MainActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }

    private void clear() {
        edtUsername.setText("");
        edtPassword.setText("");
        edtStudentName.setText("");
    }

    private boolean validation() {

        if (edtUsername.getText() == null || edtPassword.getText() == null || edtStudentName.getText() == null) return false;

        String username = edtUsername.getText().toString();
        String password = edtPassword.getText().toString();
        String studentName = edtStudentName.getText().toString();

        return username.length() != 0 && password.length() != 0 && studentName.length() != 0;
    }

    private boolean loginCheck() {
        if (edtUsername.getText() == null || edtPassword.getText() == null) return false;

        String username = edtUsername.getText().toString();
        String password = edtPassword.getText().toString();

        if (username.equals("student1") && password.equals("123456")) {
            return true;
        }

        return false;
    }

    private void goToNextPage() {
        String studentName = edtStudentName.getText().toString();

        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        intent.putExtra(SecondActivity.BUNDLE_STUDENT_NAME, studentName);
        startActivity(intent);

    }
}
