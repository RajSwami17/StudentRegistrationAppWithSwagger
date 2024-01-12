package com.student.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.student.entity.Course;
import com.student.exception.CourseNotFoundException;
import com.student.exception.StudentNotFoundException;
import com.student.repo.CourseRepo;

@Service
public class CourseServiceImpl implements CourseService
{
	
	@Autowired
	private CourseRepo courseRepo;

	@Override
	public Course saveCourse(Course course) 
	{
		return courseRepo.save(course);
	}

	@Override
	public List<Course> getAllCourses() 
	{
		List<Course> allCourses = courseRepo.findAll();
		if(allCourses == null)
		{
			throw new StudentNotFoundException("Course Not Found");
		}
		return allCourses;
	}

	@Override
	public Course updateCourse(Long courseId, Course course) 
	{
		Optional<Course> optional = courseRepo.findById(courseId);
		if(optional.isPresent())
		{
			Course updatedCourse = optional.get();
			updatedCourse.setCourseName(course.getCourseName());
			updatedCourse.setDescription(course.getDescription());
			updatedCourse.setDuration(course.getDuration());
			updatedCourse.setFees(course.getFees());
			updatedCourse.setStudent(course.getStudent());
			return courseRepo.save(updatedCourse);
		}
		else
		{
			throw new CourseNotFoundException("Course With Id" + courseId + "Not Exist");
		}
	}

	@Override
	public Course getCourseById(Long courseId) 
	{
		return courseRepo.findById(courseId)
				.orElseThrow(()-> new CourseNotFoundException("Course With Given Id" + courseId + "Not Found"));
	}

	@Override
	public String deleteCourseById(Long courseId) 
	{
		if(courseRepo.existsById(courseId))
		{
			courseRepo.deleteById(courseId);
			return "Course With Id" + courseId + "Deleted Successfully..!!";
		}
		else
		{
			throw new CourseNotFoundException("Course With Id" + courseId + "Not Exist");
		}
	}
}
