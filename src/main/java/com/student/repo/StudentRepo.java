package com.student.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.student.entity.Student;

public interface StudentRepo extends JpaRepository<Student,Long>
{
	@Query(value = "SELECT * FROM student_details WHERE email_address = :email", nativeQuery = true)
	Student findStudentByEmail(@Param("email") String email);
	
	
}
