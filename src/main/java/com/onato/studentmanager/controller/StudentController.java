package com.onato.studentmanager.controller;

import com.onato.studentmanager.dto.SubjectTopperDTO;
import com.onato.studentmanager.exceptions.ResourceNotFoundException;
import com.onato.studentmanager.model.Marks;
import com.onato.studentmanager.model.Student;
import com.onato.studentmanager.service.MarkService;
import com.onato.studentmanager.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@CrossOrigin("*")
@RestController
@RequestMapping("api/v1")
public class StudentController {
    private static final Logger log = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    StudentService studentService;

    @Autowired
    MarkService markService;

    @RequestMapping(value="/students/{id}", method= RequestMethod.GET, headers="Accept=application/json")
    public ResponseEntity<Student> getStudentByID(@PathVariable("id") long id){
        Optional<Student> student = studentService.getStudentById(id);
        HttpHeaders headers = new HttpHeaders();
        if(!student.isPresent()){
//            ExceptionResponse response = new ExceptionResponse("id","Not Found");
            throw new ResourceNotFoundException("Student not found for ID: " + id);
        }
        return new ResponseEntity<>(student.get(),HttpStatus.OK);
    }

//    @RequestMapping(value="/students/searchbyrollnumber/{rollnumber}", method= RequestMethod.GET)
//    public ResponseEntity<List<Student>> getStudentByRollNumber(@PathVariable("rollnumber") long rollNumber){
//        List<Student> students = studentService.getStudentListByRollNumber(rollNumber);
//        for(Student student:students){
//            System.out.println(student.toString());
//            System.out.println(student.getMarks());
//        }
//
//        HttpHeaders headers = new HttpHeaders();
//        return new ResponseEntity<>(students,HttpStatus.OK);
//    }

    @RequestMapping(value="/student", method= RequestMethod.POST)
    public ResponseEntity<Object> addStudent(@RequestBody Student student){
        Student createdStudent = studentService.addStudent(student);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(createdStudent,HttpStatus.CREATED);
    }

    @RequestMapping(value="/mark/{studentID}", method= RequestMethod.PUT)
    public ResponseEntity<Object> updateMark(@PathVariable("studentID") long studentID,@RequestBody Marks mark){
        verifyIfStudentExists(studentID);
        Marks createdMark = markService.addMark(studentID,mark);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(createdMark,HttpStatus.CREATED);
    }

    @RequestMapping(value="/mark/{studentID}", method= RequestMethod.POST)
    public ResponseEntity<Object> addMark(@PathVariable("studentID") long studentID,@RequestBody Marks mark){
        verifyIfStudentExists(studentID);
        Marks createdMark = markService.addMark(studentID,mark);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(createdMark,HttpStatus.CREATED);
    }

    @RequestMapping(value="/student/{id}", method= RequestMethod.DELETE)
    public ResponseEntity<Object> deleteStudent(@PathVariable("id") long id){
        verifyIfStudentExists(id);
        studentService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value="/mark/{id}", method= RequestMethod.DELETE)
    public ResponseEntity<Object> deleteMark(@PathVariable("id") long id){
        verifyIfMarksExists(id);
        markService.deleteMarks(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value="/students", method= RequestMethod.GET)
    public ResponseEntity<Object> getAllStudentsByRollNumber(@RequestParam(required = false) Long rollNumber,@RequestParam(required = false) Integer minPercentage){
        System.out.println("Roll Number is" + rollNumber);
        List<Student> students;
        if(rollNumber == null && minPercentage == null){
            students = studentService.getStudentList();
        }else if(minPercentage != null){
            students = studentService.getStudentsMoreThanPercentage(minPercentage);
        } else{
            students = studentService.getStudentListByRollNumber(rollNumber);
        }

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(students,HttpStatus.OK);
    }

    @RequestMapping(value="/subject/toppers", method= RequestMethod.GET)
    public ResponseEntity<Object> getALlSubjectToppers(){
        List<SubjectTopperDTO> toppers = studentService.getSubjectToppers();

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(toppers,HttpStatus.OK);
    }

    private void verifyIfStudentExists(Long id){
        Optional<Student> student = studentService.getStudentById(id);
        if(!student.isPresent())
            throw new ResourceNotFoundException("Student not found for ID: " + id);
    }

    private void verifyIfMarksExists(Long id){
        Optional<Marks> marks = markService.getMarks(id);
        if(!marks.isPresent())
            throw new ResourceNotFoundException("Mark not found for ID: " + id);
    }
}
