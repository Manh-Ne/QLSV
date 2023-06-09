package com.example.studentmanagement.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.studentmanagement.ActivitySubject;
import com.example.studentmanagement.R;
import com.example.studentmanagement.model.Subject;

import java.util.ArrayList;

public class adaptersubject extends BaseAdapter {

    TextView TextViewSubjectTitle;
    TextView TextViewCredit;
    ImageButton imageDelete;
    ImageButton imageInformation;
    ImageButton imageUpdate;
    Subject subject;
    private ActivitySubject context;

    private ArrayList<Subject> ArrayListSubject;

    public adaptersubject(ActivitySubject context, ArrayList<Subject> arrayListSubject) {
        this.context = context;
        ArrayListSubject = arrayListSubject;
    }

    @Override
    public int getCount() {
        return ArrayListSubject.size();
    }

    @Override
    public Object getItem(int i) {
        return ArrayListSubject.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = inflater.inflate(R.layout.listsubject,null);

        TextViewSubjectTitle = view.findViewById(R.id.TextViewSubjectTitle);

        TextViewCredit = view.findViewById(R.id.TextViewCredit);

        imageDelete = view.findViewById(R.id.subjectdelete);

        imageInformation = view.findViewById(R.id.subjectinformation);

        imageUpdate = view.findViewById(R.id.subjectupdate);

        subject = ArrayListSubject.get(i);

        TextViewCredit.setText(subject.getNumber_of_credit()+"");
        TextViewSubjectTitle.setText(subject.getSubject_title());

        int id = subject.getId();

        imageInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.information(id);
            }
        });

        // click icon delete
        imageDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Gọi tới phương thức delete
                context.delete(id);
            }
        });

        // click icon update
        imageUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.update(id);
            }
        });
        return view;
    }
}
