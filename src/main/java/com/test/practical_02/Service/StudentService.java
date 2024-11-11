package com.test.practical_02.Service;
import com.test.practical_02.Model.Student;

import java.util.List;

public interface StudentService {
    Student saveStudent(Student student);
    List<Student> getAllStudent();
    Student getStudentById(Long id);
    Student updateStudent(Long id, Student student);
    void deleteStudent(Long id);
    List<Student> getAllStudentByEnrollment(String enrollmentYear);

    String getDepartmentByStudentId(Long studentId);
    public void removeStudentsByEnrollmentYear(String enrollmentYear);

}

