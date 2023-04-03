package com.example.studentmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ActivitySubjectInformation extends AppCompatActivity {

    TextView edtTitle, edtCredit, edtTime, edtPlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_information);

        edtCredit = findViewById(R.id.txtSubjectCredit);
        edtTitle = findViewById(R.id.txtSubjectTitle);
        edtTime = findViewById(R.id.txtSubjectTime);
        edtPlace = findViewById(R.id.txtSubjectPlace);

        // Lấy dữ liệu
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        int credit = intent.getIntExtra("credit", 0);
        String time = intent.getStringExtra("time");
        String place = intent.getStringExtra("place");

        // Gắn giá trị lên
        edtTitle.setText(title);
        edtCredit.setText(credit + "");
        edtTime.setText(time);
        edtPlace.setText(place);

    }
}