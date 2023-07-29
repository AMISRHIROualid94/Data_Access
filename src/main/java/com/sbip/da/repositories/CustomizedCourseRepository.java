package com.sbip.da.repositories;

import com.sbip.da.models.Course;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomizedCourseRepository extends BaseRepository<Course, Long> {
}
