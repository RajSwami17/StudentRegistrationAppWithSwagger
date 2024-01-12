package com.student.service;

import java.util.List;

import com.student.entity.Course;
import com.student.entity.Student;

public interface StudentService 
{
	public Student saveStudent(Student student);
	
	public List<Student> getAllStudents();
	
	public Student updateStudent(Long studentId, Student student);
	
	public Student getStudentById(Long studentId);
	
	public String deleteStudentById(Long studentId);
	
	public Student getStudentByEmail(String email);
	
	public Course addCourseForStudent(Long studentId, Course course);
}
