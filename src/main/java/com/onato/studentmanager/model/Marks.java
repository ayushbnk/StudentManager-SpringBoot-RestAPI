package com.onato.studentmanager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "Marks")
public class Marks extends AbstractEntity{

    @Column(name="subject")
    private String subjectName;

    @Column(name="marks")
    private int marksObtained;

    @ManyToOne
    @JoinColumn(name = "student_id")
    @JsonIgnore
    private Student student;

    public Marks() {

    }

    public Marks(String subjectName, int marksObtained, Student student) {
        this.subjectName = subjectName;
        this.marksObtained = marksObtained;
        this.student = student;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getMarksObtained() {
        return marksObtained;
    }

    public void setMarksObtained(int marksObtained) {
        this.marksObtained = marksObtained;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

//    @Override
//    public String toString() {
//        return "Marks{" +
//                "name='" + subjectName + '\'' +
//                ", marksObtained='" + marksObtained + '\'' +
//                '}';
//    }
}
