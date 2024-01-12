package com.student.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.student.entity.Course;
import com.student.entity.Student;
import com.student.exception.EmailInUseException;
import com.student.exception.EmptyInputException;
import com.student.exception.StudentNotFoundException;
import com.student.repo.CourseRepo;
import com.student.repo.StudentRepo;

import io.micrometer.common.util.StringUtils;

@Service
public class StudentServiceImpl implements StudentService 
{
	@Autowired
	private StudentRepo studentRepo;
	
	@Autowired
	private CourseRepo courseRepo;

	@Override
	public Student saveStudent(Student student) 
	{
		Student studentAlreadyExist = studentRepo.findStudentByEmail(student.getEmail());
		if(studentAlreadyExist != null)
		{
			throw new EmailInUseException("Student With This Email Address Is Already Exist");
		}
		return studentRepo.save(student);
	}

	@Override
	public List<Student> getAllStudents() 
	{
		List<Student> allStudents = studentRepo.findAll();
		if(allStudents == null)
		{
			throw new StudentNotFoundException("Student Not Found");
		}
		return allStudents;
	}

	@Override
	public Student updateStudent(Long studentId, Student student) 
	{
		Optional<Student> optional = studentRepo.findById(studentId);
		if(optional.isPresent())
		{
			Student updatedStudent = optional.get();
			updatedStudent.setFirstName(student.getFirstName());
			updatedStudent.setLastName(student.getLastName());
			updatedStudent.setDob(student.getDob());
			updatedStudent.setGender(student.getGender());
			updatedStudent.setEmail(student.getEmail());
			updatedStudent.setPhoneNumber(student.getPhoneNumber());
			updatedStudent.setAddress(student.getAddress());
			updatedStudent.setCourses(student.getCourses());
			return studentRepo.save(updatedStudent);
		}
		else
		{
			throw new StudentNotFoundException("Student With Id" + studentId + " Not Exist");
		}
	}

	@Override
	public Student getStudentById(Long studentId) 
	{
		return studentRepo.findById(studentId)
				.orElseThrow(()-> new StudentNotFoundException("Student With Id" + studentId + " Not Exist"));
	}

	@Override
	public String deleteStudentById(Long studentId) 
	{
		if(studentRepo.existsById(studentId))
		{
			studentRepo.deleteById(studentId);
			return "Student With Id" + studentId + " Deleted Successfully..!!";
		}
		else
		{
			throw new StudentNotFoundException("Student With Id" + studentId + " Not Exist");
		}
	}

	@Override
	public Student getStudentByEmail(String email) 
	{
		if(StringUtils.isBlank(email))
		{
			throw new EmptyInputException("Invalid Email..!");
		}
		Student getStudentByEmail = studentRepo.findStudentByEmail(email);
		if(getStudentByEmail == null)
		{
			throw new StudentNotFoundException("Student With Email Address :" + email + " Does Not Exist");
		}
		return getStudentByEmail;
	}

	@Override
	public Course addCourseForStudent(Long studentId, Course course) 
	{
		Optional<Student> optional = studentRepo.findById(studentId);
		if(optional.isEmpty())
		{
			throw new StudentNotFoundException("Student With Id " + studentId + " Does Not Exist");
		}
		course.setStudent(optional.get());
		Course addCourse = courseRepo.save(course);
		return addCourse;
	}
	
	
}
