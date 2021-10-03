package com.onato.studentmanager.service;

import com.onato.studentmanager.dto.SubjectTopperDTO;
import com.onato.studentmanager.model.Student;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface StudentService {

    public Student addStudent(Student student);

    public Optional<Student> getStudentById(Long id);

    public List<Student> getStudentList();

    public List<Student> getStudentListByRollNumber(Long rollNumber);

    public List<Student> getStudentsMoreThanPercentage(int percentage);

    public List<SubjectTopperDTO> getSubjectToppers();

    public Student updateStudent(Student student);

    public void delete(Long id);
}
