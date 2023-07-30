package com.sbip.da;

import com.sbip.da.models.Course;
import com.sbip.da.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class DaApplication implements CommandLineRunner {

	@Autowired private EntityManager entityManager;
	@Autowired
	private CourseRepository courseRepository;
	public static void main(String[] args) {
		SpringApplication.run(DaApplication.class, args);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {
//		Update criteria
		System.out.println("before : "+courseRepository.saveAll(getAllCourses()));

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaUpdate<Course> criteriaUpdate = cb.createCriteriaUpdate(Course.class);

		Root<Course> root = criteriaUpdate.from(Course.class);
		Predicate predicate = cb.equal(root.get("category"),"Spring");

		criteriaUpdate.set(root.get("name"),"New name").where(predicate);
		entityManager.createQuery(criteriaUpdate).executeUpdate();

		entityManager.flush();//only for print the new values because em keep the oldest values
		entityManager.clear();//only for print the new values because em keep the oldest values
		System.out.println("After Update: "+courseRepository.findAll());

//		Delete criteria
		CriteriaBuilder cb2 = entityManager.getCriteriaBuilder();
		CriteriaDelete<Course> criteriaDelete = cb2.createCriteriaDelete(Course.class);

		Root<Course> root2 = criteriaDelete.from(Course.class);
		Predicate predicate2 = cb.equal(root2.get("id"),1L);

		criteriaDelete.where(predicate2);
		entityManager.createQuery(criteriaDelete).executeUpdate();

		entityManager.flush();//only for print the new values because em keep the oldest values
		entityManager.clear();//only for print the new values because em keep the oldest values
		System.out.println("After Delete: "+courseRepository.findAll());



	}
	private List<Course> getAllCourses(){
		Course course1 = new Course("Other Name2",
				"Spring",
				4,
				"'Spring Boot gives all the power of the Spring Framework without all of the complexities");

		Course course2 = new Course("Rapid Spring Boot Application Development",
				"Spring",
				3,
				"'Spring Boot gives all the power of the Spring Framework without all of the complexities");

		Course course3 = new Course("Rapid Spring Boot Application Development",
				"Spring boot",
				1,
				"'Spring Boot gives all the power of the Spring Framework without all of the complexities");
		Course course4 = new Course("Rapid Spring Boot Application Development",
				"Spring",
				2,
				"'Spring Boot gives all the power of the Spring Framework without all of the complexities");
		Course course5 = new Course("Rapid Spring Boot Application Development",
				"Spring boot",
				6,
				"'Spring Boot gives all the power of the Spring Framework without all of the complexities");
		return Arrays.asList(course1,course2,course3,course4,course5);
	}
}
