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

        TextView TextViewSubjectTitle = view.findViewById(R.id.TextViewSubjectTitle);

        TextView TextViewCredit = view.findViewById(R.id.TextViewCredit);

        ImageButton imageDelete = view.findViewById(R.id.subjectdelete);

        ImageButton imageInformation = view.findViewById(R.id.subjectinformation);

        ImageButton imageUpdate = view.findViewById(R.id.subjectupdate);

        Subject subject = ArrayListSubject.get(i);

        TextViewCredit.setText(subject.getNumber_of_credit());
        TextViewSubjectTitle.setText(subject.getSubject_title());

        int id = subject.getId();

        imageInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        imageDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        imageUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return null;
    }
}
