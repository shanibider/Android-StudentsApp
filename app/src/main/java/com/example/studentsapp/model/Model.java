package com.example.studentsapp.model;

import java.util.LinkedList;
import java.util.List;

public class Model {
    private static final Model _instance = new Model();

    public static Model instance(){
        return _instance;
    }

    private Model(){
        for (int i = 0; i < 5; i++) {
            addStudent(new Student("name " +i,""+i,"", false, "",""));
        }
    }

    List<Student> data = new LinkedList<>();

    public List<Student> getAllStudents(){
        return data;
    }

    public void addStudent(Student st){
        data.add(st);
    }




    public void removeStudent(int index){
        data.remove(index);
    }


}
