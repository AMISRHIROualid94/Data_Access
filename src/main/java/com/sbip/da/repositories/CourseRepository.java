package com.sbip.da.repositories;


import com.sbip.da.models.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends CrudRepository<Course,Long> {
}
