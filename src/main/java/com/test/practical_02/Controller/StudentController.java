package com.test.practical_02.Controller;

import com.test.practical_02.Implementation.StudentServiceImplementation;
import com.test.practical_02.Model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
@CrossOrigin(origins = "*")
public class StudentController {
    @Autowired
    private StudentServiceImplementation studentServiceImplementation;

    @PostMapping("/add")
    public ResponseEntity<Student> saveEmployee(@RequestBody Student student){
        return new ResponseEntity<Student>(studentServiceImplementation.saveStudent(student), HttpStatus.CREATED);
    }

    //GetAll Rest Api
    @GetMapping("/getAll")
    public List<Student> getAllStudent(){
        return studentServiceImplementation.getAllStudent();
    }

    //Get by Id Rest Api
    @GetMapping("/getById/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") long studentID){
        return new ResponseEntity<Student>(studentServiceImplementation.getStudentById(studentID),HttpStatus.OK);
    }
    @GetMapping("/getByYear/{year}")
    public ResponseEntity<List<Student>> getAllStudentByEnrollment(@PathVariable("year") String enrollmentYear){
        List<Student> students = studentServiceImplementation.getAllStudentByEnrollment(enrollmentYear);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable("id") Long id, @RequestBody Student student){
        return new ResponseEntity<Student>(studentServiceImplementation.updateStudent(id,student),HttpStatus.OK);
    }

    //Delete Rest Api
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") Long id){
        studentServiceImplementation.deleteStudent(id);
        return new ResponseEntity<String>("Student deleted Successfully.",HttpStatus.OK);
    }

    @GetMapping("/getByDepartment/{studentId}")
    public ResponseEntity<String> getDepartmentByStudentId(@PathVariable ("studentId") Long studentId){
        String departmentName = studentServiceImplementation.getDepartmentByStudentId(studentId);
        if (departmentName != null){
            return ResponseEntity.ok(departmentName);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/deleteByYear/{year}")
    public ResponseEntity<String> deleteByEnrollmentYear(@PathVariable ("year") String enrollmentYear){
        studentServiceImplementation.removeStudentsByEnrollmentYear(enrollmentYear);
        return ResponseEntity.ok("Students enrolled in " + enrollmentYear +" have been deleted.");
    }

}
