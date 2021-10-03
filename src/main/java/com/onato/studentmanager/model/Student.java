package com.onato.studentmanager.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
@Table(name = "STUDENTS")
public class Student extends AbstractEntity{
    @NotEmpty(message = "The student name field is required")
    private String name;

    private Long rollNo;

    @NotEmpty(message = "Class Name field is required")
    private String className;

    @OneToMany(mappedBy = "student", cascade = {CascadeType.REMOVE})
    private Set<Marks> marks;

    public Student(){

    }

    public Student(String name,Long rollNo,String className){
        this.name = name;
        this.rollNo = rollNo;
        this.className = className;
    }

//    @Override
//    public String toString() {
//        return "Student{" +
//                "name='" + name + '\'' +
//                ", rollNo='" + rollNo + '\'' +
//                ", class=" + className +
//                '}';
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getRollNo() {
        return rollNo;
    }

    public void setRollNo(Long rollNo) {
        this.rollNo = rollNo;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Set<Marks> getMarks() {
        return marks;
    }

    public void setMarks(Set<Marks> marks) {
        this.marks = marks;
    }
}
