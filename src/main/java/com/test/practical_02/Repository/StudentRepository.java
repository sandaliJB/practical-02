package com.test.practical_02.Repository;

import com.test.practical_02.Model.Student;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByYearOfEnrollment(String enrollmentYear);

    @Query("SELECT s.department FROM Student s WHERE s.studentId = :studentId")
    String findDepartmentByStudentId(@Param("studentId") Long studentId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Student s WHERE s.yearOfEnrollment = :enrollmentYear")
    void deleteByEnrollmentYear(@Param("enrollmentYear") String enrollmentYear);
}
