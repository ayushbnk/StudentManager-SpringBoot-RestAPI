package com.onato.studentmanager.service;


import com.onato.studentmanager.dto.SubjectTopperDTO;
import com.onato.studentmanager.model.Student;
import com.onato.studentmanager.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class StudentServiceImpl implements StudentService{
    private static final Logger log = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    public List<Student> getStudentList() {
        return studentRepository.findAll();
    }

    @Override
    public List<Student> getStudentListByRollNumber(Long rollNumber) {
        return studentRepository.findByRollNo(rollNumber);
    }

    @Override
    public List<Student> getStudentsMoreThanPercentage(int percentage) {
        return studentRepository.findStudentsHavingAtLeastPercentage(percentage);
    }

    @Override
    public List<SubjectTopperDTO> getSubjectToppers() {
        return studentRepository.getSubjectToppers();
    }

    @Override
    @Transactional
    public Student updateStudent(Student student) {
		/*Student studentFromDB = studentRepository.findOne(student.getId());
		studentFromDB.setContact(student.);*/

        return studentRepository.save(student);
    }

    @Override
    public void delete(Long id) {
        studentRepository.deleteById(id);
    }

}