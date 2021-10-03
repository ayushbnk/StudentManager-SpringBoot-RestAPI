package com.onato.studentmanager.service;

import com.onato.studentmanager.model.Marks;
import com.onato.studentmanager.model.Student;
import com.onato.studentmanager.repository.MarkRepository;
import com.onato.studentmanager.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.error.Mark;

import java.util.Optional;

@Component
public class MarkServiceImpl implements MarkService{
    @Autowired
    private MarkRepository markRepository;
    @Autowired
    private StudentRepository studentRepository;


    @Override
    public Marks addMark(Long studentID,Marks mark) {
        Student student = studentRepository.getById(studentID);
        mark.setStudent(student);
        return markRepository.save(mark);
    }

    @Override
    public void deleteMarks(Long markID) {
        markRepository.deleteById(markID);
    }

    @Override
    public Optional<Marks> getMarks(Long markID) {
        return markRepository.findById(markID);
    }
}
