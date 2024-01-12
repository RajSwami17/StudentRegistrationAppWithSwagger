package com.student;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@SpringBootApplication
public class StudentRegistrationApplication 
{
	@Bean
	public OpenAPI openAPI() {
	  return new OpenAPI()
	      .info(new Info()
	          .title("StudentRegistrationAppAPI")
	          .description("This API is for Student Registration."));
	}
	
	public static void main(String[] args) {
		SpringApplication.run(StudentRegistrationApplication.class, args);
	}

}
