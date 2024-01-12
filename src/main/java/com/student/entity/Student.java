package com.student.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Student_Details")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Student 
{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_student")
	@GenericGenerator(name = "seq_student", strategy = "increment")
	@Column(name="Student_Id",nullable = false)
	private Long studentId;
	
	@Column(name="First_Name", nullable = false)
	@NotBlank(message = "{student.firstName.notBlank}")
	@Size(max = 50, message = "{student.firstName.maxSize}")
	private String firstName;
	
	@Column(name="Last_Name", nullable = false)
	@NotBlank(message = "{student.lastName.notBlank}")
	@Size(max = 50, message = "{student.lastName.maxSsize}")
	private String lastName;
	
	@Column(name="Date_Of_Birth", nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "{student.dateOfBirth.notNull}")
	@Past(message = "{student.dateOfBirth.past}")
	private LocalDate dob;
	
	@Column(name="Gender", nullable = false)
	private String gender;
	
	@Column(name="Email_Address", nullable = false)
	@NotBlank(message = "{student.email.notBlank}")
	@Size(max = 254, message = "{student.email.maxSize}")
	@Email(message = "{student.email.notValid}", regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")
	private String email;
	
	@Column(name="Phone_Number", nullable = false)
	//@NotNull(message = "Contact number cannot be null")
    //@Pattern(regexp = "^[0-9]{10}$", message = "Invalid contact number format")
	private Long phoneNumber;
	
	@Column(name="Address", nullable = false)
	private String address;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="student")
	@JsonIgnore
	private List<Course> courses = new ArrayList<>();
}
