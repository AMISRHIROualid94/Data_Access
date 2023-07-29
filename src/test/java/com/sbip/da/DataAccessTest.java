package com.sbip.da;

import com.sbip.da.models.Course;
import com.sbip.da.repositories.CourseRepository;
import com.sbip.da.repositories.CustomizedCourseRepository;
import com.sbip.da.repositories.PagingCourseRepository;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;
@DataJpaTest
public class DataAccessTest {

    @Autowired private CourseRepository courseRepository;
    @Autowired private CustomizedCourseRepository customizedCourseRepository;
    @Autowired private PagingCourseRepository pagingCourseRepository;
    private int count = 0;
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
    @Test
    public void givenCreateCourseWhenFindAllCoursesThenExpectOneCourse(){
        Course course = new Course("Rapid Spring Boot Application Development",
                "Spring",
                4,
                "'Spring Boot gives all the power of the Spring Framework without all of the complexities");

        customizedCourseRepository.save(course);
        assertThat(Collections.singletonList(customizedCourseRepository.findAll()).size()).isEqualTo(1);
    }
    @Autowired EntityManager entityManager;
    @Test
    public void givenCreateCourseWhenLoadTheCourseThenExpectSameCourse2(){
        Course course = new Course("Other Name2",
                "Spring",
                4,
                "'Spring Boot gives all the power of the Spring Framework without all of the complexities");

        Course course2 = new Course("The Rapid Spring Boot Application Development",
                "Spring boot",
                4,
                "'Spring Boot gives all the power of the Spring Framework without all of the complexities");

        Course course3 = new Course("Rapid Spring Boot Application Development",
                "Spring boot",
                4,
                "'Spring Boot gives all the power of the Spring Framework without all of the complexities");
            Iterable<Course> courseList = courseRepository.saveAll(Arrays.asList(course,course2,course3));

        assertThat(Collections.singletonList(courseRepository.findAllByCategory("Spring")).size()).isGreaterThan(0);
        assertThat(((Collection<?>) courseRepository.findByNameOrCategory("Other Name2","Spring boot"))).hasSize(3);

        assertThat(courseRepository.existsByName("Other Name2")).isTrue();
        assertThat(courseRepository.countByCategory("Spring boot")).isEqualTo(2);
        System.out.println(courseRepository.findByNameStartsWith("Rapid"));
        System.out.println(courseRepository.findAllByCategoryOrderByName("Spring boot"));
        System.out.println("before update : "+courseRepository.findById(1L));
        courseRepository.updateCourseNameById("New Name",1L);
        System.out.println("after update: "+courseRepository.findById(1L));
    }

    @Test
    public void givenDataAvailableWhenLoadFirstPageThenGetFiveRecords(){
        Course course1 = new Course("Other Name2",
                "Spring",
                4,
                "'Spring Boot gives all the power of the Spring Framework without all of the complexities");

        Course course2 = new Course("Rapid Spring Boot Application Development",
                "Spring boot",
                3,
                "'Spring Boot gives all the power of the Spring Framework without all of the complexities");

        Course course3 = new Course("Rapid Spring Boot Application Development",
                "Spring boot",
                1,
                "'Spring Boot gives all the power of the Spring Framework without all of the complexities");
        Course course4 = new Course("Rapid Spring Boot Application Development",
                "Spring boot",
                2,
                "'Spring Boot gives all the power of the Spring Framework without all of the complexities");
        Course course5 = new Course("Rapid Spring Boot Application Development",
                "Spring boot",
                6,
                "'Spring Boot gives all the power of the Spring Framework without all of the complexities");
         pagingCourseRepository.saveAll(Arrays.asList(course1,course2,course3,course4,course5));
//        Pageable pageable = PageRequest.of(0,3);
//        assertThat(pagingCourseRepository.findAll(pageable)).hasSize(3);
//        assertThat(pageable.getPageNumber()).isEqualTo(0);
//        System.out.println("data : "+pagingCourseRepository.findAll(pageable).getContent());
        System.out.println(getCoursesByPageAndSize(4,3));
        System.out.println(getCoursesByPageAndSizeSorted(0,5,"rating"));
        Condition<Course> courseCondition = new Condition<Course>(){
            @Override
            public boolean matches(Course course) {
                return course.getId() == 5 && course.getName().equals("Rapid Spring Boot Application Development");
            }
        };
        assertThat(getCoursesByPageAndSizeSorted(0,5,"rating")).first().has(courseCondition);
    }

    public Iterable<Course> getCoursesByPageAndSize(int pageNumber,int sizePerPage){
        Pageable pageable = PageRequest.of(pageNumber,sizePerPage);
        return pagingCourseRepository.findAll(pageable).getContent();
    }

    public Iterable<Course> getCoursesByPageAndSizeSorted(int pageNumber, int sizePerPage, String sortedby){
        Pageable pageable = PageRequest.of(pageNumber,sizePerPage, Sort.by(sortedby).descending());
        return pagingCourseRepository.findAll(pageable).getContent();
    }
}
