package com.sbip.da;

import com.sbip.da.models.Course;
import com.sbip.da.repositories.CourseRepository;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.transaction.Transaction;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
public class CourseTrackerSpringBootApplicationTests {

    @Autowired private CourseRepository courseRepository;
    @Autowired private EntityManager entityManager;

    @Test
    @Transactional
    public void givenCoursesCreatedWhenLoadCoursesWithQueryThenExpectCorrectCourseDetails(){
//        Select Criteria
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Course> criteriaQuery = criteriaBuilder.createQuery(Course.class);

        Root<Course> courseRoot = criteriaQuery.from(Course.class);
        Predicate predicate = criteriaBuilder.equal(courseRoot.get("category"),"Spring");
        criteriaQuery.where(predicate);
        TypedQuery<Course> query = entityManager.createQuery(criteriaQuery);

        assertThat(query.getResultList().size()).isEqualTo(3);
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
