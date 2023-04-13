package com.example.studentmanagement;

import androidx.appcompat.app.AppCompatActivity;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.studentmanagement.database.database;
import com.example.studentmanagement.model.Student;

public class ActivityUpdateStudent extends AppCompatActivity {
    EditText editTextUpdateName , editTextUpdateCode , editTextUpdateBirthday;
    RadioButton radioButtonUpdateMale,radioButtonUpdateFemale;
    Button btnUpdateStudent;
    com.example.studentmanagement.database.database database;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_student);

        editTextUpdateName = findViewById( R.id.EditUpdateNameStudent);
        editTextUpdateCode = findViewById(R.id.EditUpdateStudentCode);
        editTextUpdateBirthday = findViewById(R.id.EditUpdateDateofBirth);

        radioButtonUpdateMale = findViewById(R.id.radiobuttonUpdateMale);
        radioButtonUpdateFemale = findViewById(R.id.radiobuttonUpdateFemale);

        btnUpdateStudent = findViewById(R.id.bt_Update_SinhVien);
        //lay du lieu inten
        Intent intent = getIntent();
        int id = intent.getIntExtra("id",0);
        String name = intent.getStringExtra("name");
        String sex = intent.getStringExtra("sex");
        String code = intent.getStringExtra("code");
        String birthday = intent.getStringExtra("birthday");
        int id_subject = intent.getIntExtra("id_subject",0);

        //gan gtri len editText va redbutton
        editTextUpdateName.setText(name);
        editTextUpdateBirthday.setText(birthday);
        editTextUpdateCode.setText(code);

        if (sex.equals(radioButtonUpdateMale)){
            radioButtonUpdateMale.setChecked(true);
            radioButtonUpdateFemale.setChecked(false);
        }
        else if (sex.equals(radioButtonUpdateFemale)){
            radioButtonUpdateMale.setChecked(false);
            radioButtonUpdateFemale.setChecked(true);
        }
        database = new database(this);

        btnUpdateStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DialogUpadate(id,id_subject);
            }
        });
    }

    private void DialogUpadate(int id, int id_subject) {
        Dialog dialog = new Dialog(this);

        dialog.setContentView(R.layout.dialogupdatestudent);

        dialog.setCanceledOnTouchOutside(false);
        Button btnYes = dialog.findViewById(R.id.button_UpdateStudentYes);
         Button btnNo = dialog.findViewById(R.id.button_UpdateStudentNo);

         btnYes.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 String name = editTextUpdateName.getText().toString().trim();
                 String code = editTextUpdateName.getText().toString().trim();
                 String birthday = editTextUpdateBirthday.getText().toString().trim();

                 Student student = createStudent();
                 if (name.equals("") || code.equals("") || birthday.equals("")) {
                     Toast.makeText(ActivityUpdateStudent.this, "Did not enter enough information", Toast.LENGTH_SHORT).show();
                 } else {
                     //update
                     database.UpdateStudent(student, id);

                     //chuyen qua activity student va cap nhap danh sach sinh vien
                     Intent intent = new Intent(ActivityUpdateStudent.this, ActivityStudent.class);
                     //gui id cua subject
                     intent.putExtra("id_subject", id_subject);
                     startActivity(intent);
                     Toast.makeText(ActivityUpdateStudent.this, "more success", Toast.LENGTH_SHORT).show();
                 }
             }
         });
         btnNo.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 dialog.cancel();

             }
         });
         dialog.show();
    }
    //create student
    private Student createStudent() {
        String name = editTextUpdateName.getText().toString().trim();
        String code = editTextUpdateCode.getText().toString().trim();
        String birthday = editTextUpdateBirthday.getText().toString().trim();
        String sex = "";
        if (radioButtonUpdateMale.isChecked()) {
            sex = "Male";
        } else {
            sex = "Female";
        }

        Student student = new Student(name,sex,code,birthday);
        return student;
    }

}