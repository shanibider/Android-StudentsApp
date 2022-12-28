package com.example.studentsapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.studentsapp.model.Model;
import com.example.studentsapp.model.Student;

public class EditActivity extends AppCompatActivity {
    TextView nameIn, idIn, phoneIn, addressIn;
    Button saveBtn, cancelBtn, deleteBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Intent me = getIntent();
        int pos = me.getIntExtra("pos", 0);
        Student s = Model.instance().getAllStudents().get(pos);

        nameIn = findViewById(R.id.edit_name_et);
        idIn = findViewById(R.id.edit_id_et);
        phoneIn = findViewById(R.id.edit_phone_et);
        addressIn = findViewById(R.id.edit_address_et);
        saveBtn = findViewById(R.id.edit_save_button);
        cancelBtn = findViewById(R.id.edit_cancel_button);
        deleteBtn = findViewById(R.id.edit_delete_button);
        CheckBox checked = findViewById(R.id.checkBox);

        nameIn.setText(s.name);
        idIn.setText(s.id);
        phoneIn.setText(s.phone);
        addressIn.setText(s.address);
        checked.setChecked(s.cb);


//Listener to save button
        saveBtn.setOnClickListener(view -> {
            String name = nameIn.getText().toString();
            String id = nameIn.getText().toString();
            String phone = nameIn.getText().toString();
            String address = nameIn.getText().toString();
            boolean isChecked = checked.isChecked();

            Student new_s = new Student(name, id,"", isChecked, phone, address);
            Model.instance().getAllStudents().set(pos,new_s);

            finish();
        });

//Listener to cancel button
        cancelBtn.setOnClickListener(view -> finish());


//Listener to delete button
        deleteBtn.setOnClickListener(view -> {
           Model.instance().getAllStudents().remove(pos);

            finish();
        });







    }
}