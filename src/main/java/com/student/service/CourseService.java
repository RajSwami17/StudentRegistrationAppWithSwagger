package com.student.service;
import java.util.List;

import com.student.entity.Course;


public interface CourseService 
{
	public Course saveCourse(Course Course);
	
	public List<Course> getAllCourses();
	
	public Course updateCourse(Long CourseId, Course Course);
	
	public Course getCourseById(Long CourseId);
	
	public String deleteCourseById(Long CourseId);
}
