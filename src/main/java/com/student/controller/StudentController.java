package com.student.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.student.entity.Course;
import com.student.entity.Student;
import com.student.service.StudentService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;

@RestController
@RequestMapping("/api/student")
@Validated
public class StudentController 
{
	@Autowired
	private StudentService studentService;
	
	@PostMapping("/save")
	public ResponseEntity<Student> saveStudent(@Valid @RequestBody Student student)
	{
		Student saveStudent = studentService.saveStudent(student);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/studentId").buildAndExpand(saveStudent.getStudentId())
				.toUri();
		return ResponseEntity.created(location).build();
		
	}
	
	@GetMapping("/find/all")
	public ResponseEntity<List<Student>> getAllStudents()
	{
		List<Student> studentList = studentService.getAllStudents();
		return new ResponseEntity<>(studentList,HttpStatus.OK);
	}
	
	@GetMapping("/find/{studentId}")
	public ResponseEntity<Student> fetchStudentById(@PathVariable("studentId")Long studentId)
	{
		Student findStudentById = studentService.getStudentById(studentId);
		return new ResponseEntity<>(findStudentById,HttpStatus.OK);
	}
	
	@GetMapping("/find/{email}")
	public ResponseEntity<Student> fetchStudentByEmail(@PathVariable("email")@Email(message = "{student.email.notValid}")String email)
	{
		Student findStudentByEmail = studentService.getStudentByEmail(email);
		return new ResponseEntity<>(findStudentByEmail,HttpStatus.OK);
	}
	
	@PutMapping("/update/{studentId}")
	public ResponseEntity<Student> modifyStudent(@PathVariable("studentId")Long studentId, @Valid @RequestBody Student student)
	{
		Student updatedStudent = studentService.updateStudent(studentId, student);
		return new ResponseEntity<>(updatedStudent,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{studentId}")
	public ResponseEntity<String> removeStudent(@PathVariable("studentId")Long studentId)
	{
		studentService.deleteStudentById(studentId);
		return new ResponseEntity<>("Student With Id: "+ studentId + " Deleted Successfully",HttpStatus.OK);
	}
	
	@PostMapping("/{studentId}/course")
	public ResponseEntity<Course> addCourseForStudent(@PathVariable("studentId")Long studentId,@Valid @RequestBody Course course)
	{
		Course savedCourse = studentService.addCourseForStudent(studentId, course);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/studentId").buildAndExpand(savedCourse.getCourseId()).toUri();
		return ResponseEntity.created(location).build();
	}
}
