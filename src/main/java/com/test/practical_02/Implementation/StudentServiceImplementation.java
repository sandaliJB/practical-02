package com.test.practical_02.Implementation;
import com.test.practical_02.Model.Student;
import com.test.practical_02.Repository.StudentRepository;
import com.test.practical_02.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImplementation implements StudentService {
    @Autowired
    private StudentRepository studentRepo;

    @Override
    public Student saveStudent(Student student){
        return studentRepo.save(student);
    }
    //get all employee form database
    @Override
    public List<Student> getAllStudent() {
        return studentRepo.findAll();
    }

    //get employee using id
    @Override
    public List<Student> getAllStudentByEnrollment(String enrollmentYear) {
        return studentRepo.findByYearOfEnrollment(enrollmentYear);
    }
    @Override
    public Student getStudentById(Long id) {
        Optional<Student> student = studentRepo.findById(id);
        if(student.isPresent()){
            return student.get();
        }else {
            throw new RuntimeException();
        }
    }
    @Override
    public Student updateStudent(Long id, Student student) {
        Student existingStudent = studentRepo.findById(id).orElseThrow(()-> new RuntimeException());
        existingStudent.setFirstName(student.getFirstName());
        existingStudent.setLastName(student.getLastName());
        existingStudent.setEmail(student.getEmail());
        existingStudent.setDepartment(student.getEmail());
        existingStudent.setYearOfEnrollment(student.getEmail());

        studentRepo.save(existingStudent);
        return existingStudent;
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepo.findById(id).orElseThrow(() -> new RuntimeException());
        studentRepo.deleteById(id);
    }

    @Override
    public String getDepartmentByStudentId(Long studentId){
        return studentRepo.findDepartmentByStudentId(studentId);
    }
    @Override
    public void removeStudentsByEnrollmentYear(String enrollmentYear) {
        studentRepo.deleteByEnrollmentYear(enrollmentYear);
    }
}
