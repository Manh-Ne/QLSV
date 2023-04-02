package com.example.studentmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.studentmanagement.database.database;
import com.example.studentmanagement.model.Subject;

public class ActivityAddSubject extends AppCompatActivity {

    Button buttonAddSubject,btnYes,btnNo;
    EditText editSubjectTitle, edtSubjectCredit, edtSubjectTime, edtSubjectPlace;
    com.example.studentmanagement.database.database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subject);

        buttonAddSubject = findViewById(R.id.buttonAddSubject);
        edtSubjectCredit = findViewById(R.id.EditTextSubjectCredit);
        edtSubjectPlace = findViewById(R.id.EditTextSubjectPlace);
        edtSubjectTime = findViewById(R.id.EditTextSubjectTime);
        editSubjectTitle = findViewById(R.id.EditTextSubjectTitle);

        database = new database(this);

        buttonAddSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogAdd();
               // Toast.makeText(ActivityAddSubject.this,"Add subject success", Toast.LENGTH_SHORT).show();
            }
        });
    }
    // createn subject
    private void DialogAdd() {
        //tạo đối tg cửa sổ
        Dialog dialog = new Dialog(this);

        // nạp layout vào dialog
        dialog.setContentView(R.layout.dialogadd);
        //tắt click ngoài là thoát
        dialog.setCanceledOnTouchOutside(false);
        btnYes = dialog.findViewById(R.id.buttonYes);
        btnNo = dialog.findViewById(R.id.buttonNo);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String subjecttitle = editSubjectTitle.getText().toString().trim();
                String credit = edtSubjectCredit.getText().toString().trim();
                String time = edtSubjectTime.getText().toString().trim();
                String place = edtSubjectPlace.getText().toString().trim();
            //nếu dữ liệu chưa nhập đầy đủ
                if (subjecttitle.equals("") || credit.equals("") || time.equals("") || place.equals("")){
                    Toast.makeText(ActivityAddSubject.this,"Did not enter information ",Toast.LENGTH_SHORT).show();
                }
                else {
                    //gán cho đối tg subject giá trị đc nhập vào
                    Subject subject = CreatSubject();
                    // add trong database
                    database.AddSubjects(subject);

                    // add thành công thì chuyển qua giao diện subject
                    Intent intent = new Intent(ActivityAddSubject.this,ActivitySubject.class);
                    startActivity(intent);

                    Toast.makeText(ActivityAddSubject.this,"more success", Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(ActivityAddSubject.this,"more success", Toast.LENGTH_SHORT).show();
            }
        });
        //nếu ko add nữa
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
        //show dialog
        dialog.show();
    }
    private Subject CreatSubject(){
        String subjecttitle = editSubjectTitle.getText().toString().trim();
        int credit = Integer.parseInt(edtSubjectCredit.getText().toString().trim());
        String time = edtSubjectTime.getText().toString().trim();
        String place = edtSubjectPlace.getText().toString().trim();

        Subject subject = new Subject(subjecttitle,credit,time,place);
        return subject;
    }
}