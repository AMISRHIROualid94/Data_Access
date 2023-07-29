package com.sbip.da;

import com.sbip.da.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DaApplication {

	@Autowired
	private CourseRepository courseRepository;
	public static void main(String[] args) {
		SpringApplication.run(DaApplication.class, args);
	}

}
