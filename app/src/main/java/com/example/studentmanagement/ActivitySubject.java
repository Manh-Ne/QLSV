package com.example.studentmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.widget.Toolbar;


import com.example.studentmanagement.adapter.adaptersubject;
import com.example.studentmanagement.database.database;
import com.example.studentmanagement.model.Subject;

import java.util.ArrayList;

public class ActivitySubject extends AppCompatActivity {

    Toolbar toolbar;
    ListView listViewsubject;
    ArrayList<Subject> ArrayListSubject;
    com.example.studentmanagement.database.database database;
    com.example.studentmanagement.adapter.adaptersubject adaptersubject;
    int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);

        toolbar = findViewById(R.id.toolbarSubject);
        listViewsubject = findViewById(R.id.listviewSubject);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        database = new database(this);

        ArrayListSubject = new ArrayList<>();

        Cursor cursor = database.getDataSubject();
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String title = cursor.getString(1);
            int credit = cursor.getInt(2);
            String time = cursor.getString(3);
            String place = cursor.getString(4);

            ArrayListSubject.add(new Subject(id, title, credit, time, place));
        }
        adaptersubject = new adaptersubject(ActivitySubject.this,ArrayListSubject);
        listViewsubject.setAdapter(adaptersubject);
        cursor.moveToFirst();
        cursor.close();
        //tạo sự kueenj click vào item subjcet
        listViewsubject.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ActivitySubject.this,ActivityStudent.class);
                int id_subject = ArrayListSubject.get(i).getId();
                //truyền dữ lieeuk id subjcet vào student
                intent.putExtra("id subject",id_subject);
                startActivity(intent);
            }
        });

    }
    //Thêm một menu add vào toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuadd,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            //nếu click vào add thì chuyển qua màn hinhg add subject
            case R.id.menuadd:
                Intent intent1 = new Intent(ActivitySubject.this,ActivityAddSubject.class);
                startActivity(intent1);
                break;

                //Nếu click vào nút còn lại là nút back thì quay lại main
            default:
                Intent intent = new Intent(ActivitySubject.this,MainActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    // nếu click back ở ddienj tohaij sẽ trở về main activyty
    @Override
    public void onBackPressed() {
        count++;
        if (count>=1){
            Intent intent = new Intent(ActivitySubject.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    }


    public void information(final int pos){
        Cursor cursor = database.getDataSubject();

        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            if (id == pos){
                Intent intent = new Intent(ActivitySubject.this, ActivitySubjectInformation.class);

                intent.putExtra("id", id);
                String title = cursor.getString(1);
                int credit = cursor.getInt(2);
                String time = cursor.getString(3);
                String place = cursor.getString(4);

                intent.putExtra("title", title);
                intent.putExtra("credit", credit);
                intent.putExtra("time", time);
                intent.putExtra("place", place);


                startActivity(intent);
            }
        }
    }

    // Phương thức xóa subject
    public void delete(final int position){
        // Đối thượng cửa sổ
        Dialog dialog = new Dialog(this);

        // Nạp layout vào dialog
        dialog.setContentView(R.layout.dialogdeletesubject);

        dialog.setCanceledOnTouchOutside(false);

        Button btnYes = dialog.findViewById(R.id.buttonYesDeleteSubject);
        Button btnNo = dialog.findViewById(R.id.buttonNoDeleteSubject);

        // Mở dialog nếu ấn Yes
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //database = new database(ActivitySubject.this);

                // Xóa subject trong csdl
                database.DeleteSubject(position);

                // xóa student
                database.DeleteSubjectStudent(position);

                // Cập nhật lại Activity Subject
                Intent intent = new Intent(ActivitySubject.this, ActivitySubject.class);
                startActivity(intent);
            }
        });

        // Đóng dialog nếu ấn No
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
        // Show dialog
        dialog.show();
    }

    public void update(final int pos){
        Cursor cursor = database.getDataSubject();

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);

            if (id == pos) {
                Intent intent = new Intent(ActivitySubject.this, ActivityUpdateSubject.class);
                intent.putExtra("id", id);

                String title = cursor.getString(1);
                int credit = cursor.getInt(2);
                String time = cursor.getString(3);
                String place = cursor.getString(4);

                // Gửi dữ liệu qua activity update
                intent.putExtra("title", title);
                intent.putExtra("credit", credit);
                intent.putExtra("time", time);
                intent.putExtra("place", place);

                startActivity(intent);
            }
        }
    }
}