package com.example.studentdbapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SearchStudent extends AppCompatActivity {
EditText ed1,ed2,ed3,ed4;
AppCompatButton  btn1,btn2;
String getAdmissionNumber,getStudentName,getRollNo,getCollege;
DatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_student);
        dbHelper = new DatabaseHelper(this);
        ed1=(EditText) findViewById(R.id.txt1);
        ed2=(EditText) findViewById(R.id.txt2);
        ed3=(EditText) findViewById(R.id.txt3);
        ed4=(EditText) findViewById(R.id.txt4);
        btn1=(AppCompatButton) findViewById(R.id.btn1);
        btn2=(AppCompatButton) findViewById(R.id.btn2);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAdmissionNumber=ed1.getText().toString();
                Cursor c=dbHelper.searchData(getAdmissionNumber);
                if (c.getCount()==0)
                {
                    ed2.setText("");
                    ed3.setText("");
                    ed4.setText("");
                    Toast.makeText(getApplicationContext(), "Invalid Admission Number", Toast.LENGTH_SHORT).show();
                }else{
                    while(c.moveToNext()){
                        getStudentName=c.getString(1);
                        getRollNo=c.getString(2);
                        getCollege=c.getString(4);
                        ed2.setText(getStudentName);
                        ed3.setText(getRollNo);
                        ed4.setText(getCollege);
                    }
                }


            }

        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });

    }
}