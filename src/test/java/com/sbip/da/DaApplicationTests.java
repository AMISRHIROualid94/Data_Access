package com.sbip.da;

import com.sbip.da.models.Course;
import com.sbip.da.repositories.CourseRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

//@DataMongoTest
//@ExtendWith(SpringExtension.class)
@SpringBootTest
class DaApplicationTests {

//	@Autowired private DataSource dataSource;
//	@Autowired private MongoTemplate mongoTemplate;
	@Autowired
	private CourseRepository courseRepository;

	@Test
	void contextLoads() {
	}

//	@Test
//	public void givenDatasourceAvailableWhenAccessDetailsThenExpectDetails() throws SQLException {
//		assertThat(dataSource.getClass().getName()).isEqualTo("com.zaxxer.hikari.HikariDataSource");
//		assertThat(dataSource.getConnection().getMetaData().getDatabaseProductName()).isEqualTo("H2");
//	}

//	@Test
//	public void givenObjectAvailableWhenSaveToCollectionThenExpectValue() throws MongoException{
//		DBObject dbObject = BasicDBObjectBuilder.start().add("Mongodb", "Mongodb is a NoSql Database").get();
//		mongoTemplate.save(dbObject,"collection");
//		assertThat(mongoTemplate.findAll(DBObject.class,"collection"))
//				.extracting("Mongodb")
//				.containsOnly("Mongodb is a NoSql Database");
//	}

	@Test
	public void givenCreateCourseWhenLoadTheCourseThenExpectSameCourse(){
		Course course = new Course(
				"Rapid Spring Boot Application Development",
				"Spring",
				4,
				"'Spring Boot gives all the power of the Spring Framework without all of the complexities");
		courseRepository.save(course);
		course.setRating(5);
		Course savedCourse = courseRepository.save(course);
		assertThat(courseRepository.findById(savedCourse.getId()).get().getRating()).isEqualTo(5);
	}

	@Test
	public void givenDeleteCourseWhenLoadTheCourseThenExpectNoCourse() {
		Course course = new Course("Rapid Spring Boot Application Development",
				"Spring",
				4,
				"'Spring Boot gives all the power of the Spring Framework without all of the complexities");
				Course savedCourse = courseRepository.save(course);
		assertThat(courseRepository.findById(savedCourse.getId()).get()).isEqualTo(course);
		courseRepository.delete(course);
		assertThat(courseRepository.findById(savedCourse.getId()).isPresent()).isFalse();
	}
}
