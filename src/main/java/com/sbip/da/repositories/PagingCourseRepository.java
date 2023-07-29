package com.sbip.da.repositories;

import com.sbip.da.models.Course;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagingCourseRepository extends PagingAndSortingRepository<Course,Long> {

}
