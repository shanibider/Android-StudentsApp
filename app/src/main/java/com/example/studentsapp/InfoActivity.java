package com.example.studentsapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studentsapp.model.Model;
import com.example.studentsapp.model.Student;


public class InfoActivity extends AppCompatActivity {
    TextView nameIn, idIn, phoneIn, addressIn;
    Button editBtn, cancelBtn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);




        //s is student in this pos
        Intent me = getIntent();                                     //Create an intent (me)
        int pos = me.getIntExtra("pos", 0);         //getIntExtra - give me value based on key. pos= the position we receive from intent
        Student s = Model.instance().getAllStudents().get(pos);     //s is student in pos position

        nameIn = findViewById(R.id.info_name_et);
        idIn = findViewById(R.id.info_id_et);
        phoneIn = findViewById(R.id.info_phone_et);
        addressIn = findViewById(R.id.info_address_et);
        editBtn = findViewById(R.id.info_edit_button);
        CheckBox checked = findViewById(R.id.info_cb);

        nameIn.setText(s.name);
        idIn.setText(s.id);
        phoneIn.setText(s.phone);
        addressIn.setText(s.address);
        checked.setChecked(s.cb);

        //open EditActivity
        editBtn.setOnClickListener(view -> {
            Intent intent = new Intent(InfoActivity.this, EditActivity.class);
            intent.putExtra("pos", pos);
            startActivity(intent);

            finish();
        });
    }
}