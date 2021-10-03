package com.onato.studentmanager.service;

import com.onato.studentmanager.model.Marks;
import com.onato.studentmanager.model.Student;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.error.Mark;

import java.util.Optional;

@Component
public interface MarkService {
    public Marks addMark(Long studentID,Marks mark);
    public void deleteMarks(Long markID);
    public Optional<Marks> getMarks(Long markID);
}
