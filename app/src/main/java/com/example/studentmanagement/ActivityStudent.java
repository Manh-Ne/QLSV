package com.example.studentmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.qlsv.R;
import com.example.studentmanagement.database.database;

public class ActivityStudent extends AppCompatActivity {
    Button buttonAddstudent;
    EditText editTextStudentName,editTextStudentCode,editTextDateofbirth;
    RadioButton radioButtonMale,radioButtonFemale;
    com.example.studentmanagement.database.database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        buttonAddstudent = findViewById(R.id.bt_addSinhVien);

        editTextStudentName = findViewById(R.id.EditTensinhvien);
        editTextStudentCode = findViewById(R.id.EditMasinhvien);
        editTextDateofbirth = findViewById(R.id.Editsinhnhat);


        radioButtonMale = findViewById(R.id.radiobuttonnam);
        radioButtonFemale = findViewById(R.id.radiobuttonnu);
//chua fix
        Intent intent = getIntent();
        


    }
}