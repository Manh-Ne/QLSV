package com.example.studentmanagement;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ActivityinformationStudent extends AppCompatActivity {
    TextView txtname,txtSex,txtCode,txtBirthday;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activityinformation_student);

        txtBirthday = findViewById(R.id.txtStudentDateofbirth);
        txtCode = findViewById(R.id.txtStudentCode);
        txtname = findViewById(R.id.txtStudentName);
        txtSex = findViewById(R.id.txtStudentSex);
//nhan du lieu
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String sex = intent.getStringExtra("sex");
        String code = intent.getStringExtra("code");
        String birthday = intent.getStringExtra("birthday");


        txtname.setText(name);
        txtSex.setText(sex);
        txtCode.setText(code);
        txtBirthday.setText(birthday);
    }
}