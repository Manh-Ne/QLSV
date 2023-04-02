package com.example.studentmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnSubject,btnAuthor,btnExit,btnYes,btnNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAuthor = findViewById(R.id.buttonAuthor);
        btnExit = findViewById(R.id.buttonExit);
        btnSubject = findViewById(R.id.buttonSubject);
        //Click tác giả
        btnAuthor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogAuthor();
            }
        });
        // click subject
        btnSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //chuyển qua activity subject
                Intent intent = new Intent(MainActivity.this,ActivitySubject.class);
                startActivity(intent);
            }
        });
        //click exit app
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogExit();
            }
        });
    }
// hàm hiển thị cửa sổ dialog exit
    private void DialogExit() {
        Dialog dialog = new Dialog(this);

        dialog.setContentView(R.layout.dialogexit);
        // tắt click ngoài là thoát
        dialog.setCanceledOnTouchOutside(false);

        btnYes = dialog.findViewById(R.id.buttonYes);
        btnNo = dialog.findViewById(R.id.buttonNo);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,MainActivity.class);
                startActivity(intent);
                // thoát
                Intent intent1 = new Intent(Intent.ACTION_MAIN);
                intent1.addCategory(Intent.CATEGORY_HOME);
                startActivity(intent1);
            }
        });
        //nếu click no thì đóng cửa sổ
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });

        dialog.show();
    }

    // Hiển thị thông tin tác giả
    private void DialogAuthor() {
        //tạo đồi tg cửa sổ dialog
        Dialog dialog = new Dialog(this);
    //nạp layout vào dialog
        dialog.setContentView(R.layout.dialoginformation);
        dialog.show();
    }
}