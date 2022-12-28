package com.example.studentsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.studentsapp.model.Model;
import com.example.studentsapp.model.Student;

public class AddStudentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        EditText nameEt = findViewById(R.id.edit_name_et);
        EditText idEt = findViewById(R.id.edit_id_et);
        EditText phoneEt = findViewById(R.id.edit_phone_et);
        EditText addressEt = findViewById(R.id.edit_address_et);
        Button saveBtn = findViewById(R.id.addstudent_save_btn);
        Button cancelBtn = findViewById(R.id.edit_delete_button);
        CheckBox checked =  findViewById(R.id.checkBox);

        saveBtn.setOnClickListener(view -> {
            String name = nameEt.getText().toString();
            String id = idEt.getText().toString();
            String phone = phoneEt.getText().toString();
            String address = addressEt.getText().toString();
            boolean isChecked = checked.isChecked();


            //Create new s
            Student s = new Student(name,id,"", isChecked,phone, address);
            Model.instance().getAllStudents().add(s);

            finish();
        });

        cancelBtn.setOnClickListener(view -> finish());
    }
}