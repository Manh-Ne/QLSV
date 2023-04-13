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

public class ActivityAddStudent extends AppCompatActivity {
    Button buttonAddstudent;
    EditText editTextStudentName, editTextStudentCode, editTextDateofbirth;
    RadioButton radioButtonMale, radioButtonFemale;
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

        Intent intent = new Intent();
        int id_subject = intent.getIntExtra("id_subject", 0);
        
        database = new database(this);
        buttonAddstudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogAdd(id_subject);
            }
        });
                
    }

    private void DialogAdd(int id_subject) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialogaddstudent);
        
        Button btnYes = dialog.findViewById(R.id.buttonYesAddStudent);
        Button btnNo = dialog.findViewById(R.id.buttonNoAddStudent);
        
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextStudentName.getText().toString().trim();
                String code = editTextStudentCode.getText().toString().trim();
                String birthday = editTextDateofbirth.getText().toString().trim();
                String sex = "";
                
                if (radioButtonMale.isChecked()){
                    sex = "Male";
                }
                else if (radioButtonFemale.isChecked()){
                    sex = "Female";
                }
                if (name.equals("") || code.equals("") || birthday.equals("") || sex.equals("")){
                    Toast.makeText(ActivityAddStudent.this, "Did not enter enough information", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(ActivityAddStudent.this, ActivityStudent.class);
                    intent.putExtra("id_subject", id_subject);
                    startActivity(intent);

                    Toast.makeText(ActivityAddStudent.this, "more success", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    
    private Student CreateStudent(int id_subject){
        String name = editTextStudentName.getText().toString().trim();
        String code = editTextStudentCode.getText().toString().trim();
        String birthday = editTextDateofbirth.getText().toString().trim();
        String sex = "";

        if (radioButtonMale.isChecked()){
            sex = "Male";
        }
        else if (radioButtonFemale.isChecked()){
            sex = "Female";
        }
        Student student = new Student(name, sex, code, birthday, id_subject);
        return student;
    }
    
}