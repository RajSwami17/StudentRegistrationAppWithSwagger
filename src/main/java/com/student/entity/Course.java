package com.student.entity;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Course_Details")
@AllArgsConstructor
@NoArgsConstructor
@Data	
public class Course 
{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_course")
	@GenericGenerator(name = "seq_course", strategy = "increment")
	@Column(name="Cousre_Id", nullable = false)
	private Long courseId;
	
	@Column(name="Course_Name", nullable = false)
	private String courseName;
	
	@Column(name="Description", nullable = false)
	private String description;
	
	@Column(name="Course_Duration", nullable = false)
	private String duration;
	
	@Column(name="Course_Fees", nullable = false)
	private Double fees;
		
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="Student_Id")
	@JsonIgnore
	private Student student;
}
