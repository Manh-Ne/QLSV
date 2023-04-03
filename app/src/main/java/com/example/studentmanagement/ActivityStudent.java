package com.example.studentmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;

import com.example.studentmanagement.adapter.adapterstudent;
import com.example.studentmanagement.database.database;
import com.example.studentmanagement.model.Student;

import java.util.ArrayList;

public class ActivityStudent extends AppCompatActivity {

    Toolbar toolbar;
    ListView listViewstudent;

    ArrayList<Student> ArrayListStudent;
    adapterstudent adapterstudent;

    int id_subject = 0;
    int counter = 0;

    Button buttonAddstudent;
    EditText editTextStudentName,editTextStudentCode,editTextDateofbirth;
    RadioButton radioButtonMale,radioButtonFemale;
    database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        toolbar = findViewById(R.id.toolbarstudent);
        listViewstudent = findViewById(R.id.listviewstudent);

        Intent intent = new Intent();

        id_subject = intent.getIntExtra("id_subject", 0);

        //toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        database = new database(this);

        ArrayListStudent = new ArrayList<>();
        ArrayListStudent.clear();

        Cursor cursor = database.getDataStudent(id_subject);
        while (cursor.moveToNext()){
            int id_sub = cursor.getInt(5);
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String sex = cursor.getString(2);
            String code = cursor.getString(3);
            String birthday = cursor.getString(4);

            ArrayListStudent.add(new Student(id, name, sex, code, birthday, id_sub));
        }
        adapterstudent = new adapterstudent(ActivityStudent.this, ArrayListStudent);
        //Hiển thị listview
        listViewstudent.setAdapter(adapterstudent);
        cursor.moveToFirst();
        cursor.close();



        buttonAddstudent = findViewById(R.id.bt_addSinhVien);

        editTextStudentName = findViewById(R.id.EditTensinhvien);
        editTextStudentCode = findViewById(R.id.EditMasinhvien);
        editTextDateofbirth = findViewById(R.id.Editsinhnhat);


        radioButtonMale = findViewById(R.id.radiobuttonnam);
        radioButtonFemale = findViewById(R.id.radiobuttonnu);
//chua fix
//        Intent intent = getIntent();
        


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menuaddstudent, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        switch (item.getItemId()){
            case R.id.menuaddstudent:

                Intent intent = new Intent(ActivityStudent.this, ActivityAddStudent.class);
                intent.putExtra("id_subject", id_subject);
                startActivity(intent);
                break;

            default:
                Intent intent1 = new Intent(ActivityStudent.this, ActivitySubject.class);
                startActivity(intent1);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    // back tren dien thoai ra subject activity
    @Override
    public void onBackPressed() {

        counter++;
        if (counter>=1){
            Intent intent = new Intent(this, ActivitySubject.class);
            startActivity(intent);
            finish();
        }
    }
}