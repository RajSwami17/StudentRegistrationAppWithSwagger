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
import com.student.service.CourseService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/course")
@Validated
public class CourseController 
{
	@Autowired
	private CourseService courseService;
	
	@PostMapping("/save")
	public ResponseEntity<Course> registerCourse(@Valid @RequestBody Course course)
	{
		Course registerCourse = courseService.saveCourse(course);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/courseId").buildAndExpand(registerCourse.getCourseId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@GetMapping("/find/all")
	public ResponseEntity<List<Course>> fetchAllCourses()
	{
		List<Course> courseList = courseService.getAllCourses();
		return new ResponseEntity<>(courseList, HttpStatus.OK);
	}
	
	@GetMapping("/find/{courseId}")
	public ResponseEntity<Course> fetchCourseById(@PathVariable("courseId")Long courseId)
	{
		Course findCourseById = courseService.getCourseById(courseId);
		return new ResponseEntity<>(findCourseById,HttpStatus.OK);
	}
	
	@PutMapping("/update/{courseId}")
	public ResponseEntity<Course> modifyCourse(@PathVariable("courseId")Long courseId,@Valid @RequestBody Course course)
	{
		Course updatedCourse = courseService.updateCourse(courseId, course);
		return new ResponseEntity<>(updatedCourse,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{courseId}")
	public ResponseEntity<String> removeCourse(@PathVariable("courseId")Long courseId)
	{
		courseService.deleteCourseById(courseId);
		return new ResponseEntity<>("Course With Id: "+ courseId + " Deleted Successfully",HttpStatus.OK);
	}
}
