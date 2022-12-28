package com.example.studentsapp;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.studentsapp.model.Model;
import com.example.studentsapp.model.Student;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
//view is element in the view. In our project is row

public class StudentRecyclerList extends AppCompatActivity {
    List<Student> data;
    FloatingActionButton add_button;

    StudentRecyclerAdapter adapter;

    TextView nameTv;
    TextView idTv;
    CheckBox cb;

  /* EXTRA CODE
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus) {
            data = Model.instance().getAllStudents();
            adapter.notifyDataSetChanged();
        }
    }*/


    //onCreate
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_recycler_list);

        //Receive data from Model
        data = Model.instance().getAllStudents();

        RecyclerView list = findViewById(R.id.studentrecycler_list);
        list.setHasFixedSize(true);

        //Layout - setting the view
        //Adapter- object that connect between data and view(ui)
        list.setLayoutManager(new LinearLayoutManager(this));
        StudentRecyclerAdapter adapter = new StudentRecyclerAdapter();
        list.setAdapter(adapter);   //setting adapter

        //catch row press
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            //Logic- what happen on line get clicked
            public void onItemClick(int pos) {
                Log.d("TAG", "Row was clicked" + pos);
            }
        });

        //New code
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //intent- here we intent on new activity (opening new screen)
                Intent intent = new Intent(StudentRecyclerList.this, AddStudentActivity.class);
                startActivity(intent);
            }
        });
    }





    //ViewHolder- Class that wrap the view. viewHolder save the specific view for each row (like the cb example)
    class StudentViewHolder extends RecyclerView.ViewHolder {
        TextView nameTv;
        TextView idTv;
        CheckBox cb;

        public StudentViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            nameTv = itemView.findViewById(R.id.studentlistrow_name_tv);
            idTv = itemView.findViewById(R.id.studentlistrow_id_tv);
            cb = itemView.findViewById(R.id.studentlistrow_cb);

            cb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = (int) cb.getTag();     //cb position
                    Student st = data.get(pos);     //return Element in specific pos
                    st.cb = cb.isChecked();
                }
            });

            //In recyclerView we cant press a row, without a Listener.
            //itemView is the view the CTOR receive (a row)
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();             //Adapter contain the data.
                    listener.onItemClick(pos);
                    //new code:
                    //open InfoActivity
                    Intent intent = new Intent(StudentRecyclerList.this, InfoActivity.class);
                    intent.putExtra("pos", pos);        //pos of clicked row
                    startActivity(intent);
                }
            });
        }

        //Bind student values from linkedlist to xml
        public void bind(Student st, int pos) {
            nameTv.setText(st.name);            //our new variable (nameTv) equals to name of student from linked-list
            idTv.setText(st.id);
            cb.setChecked(st.cb);
            cb.setTag(pos);
        }
    }



    public interface OnItemClickListener {
        void onItemClick(int pos);
    }


    //Adapter
    class StudentRecyclerAdapter extends RecyclerView.Adapter<StudentViewHolder> {
        OnItemClickListener listener;

        void setOnItemClickListener(OnItemClickListener listener) {
            this.listener = listener;
        }

        //When viewHolder created
        @NonNull
        @Override
        public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.student_list_row, parent, false);
            return new StudentViewHolder(view, listener);
        }

        //Bind the date from view to each viewHolder
        @Override
        public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
            Student st = data.get(position);       //getting the student from viewHolder
            holder.bind(st, position);             //setting the view fields according to student position, with bind function
        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }

}



