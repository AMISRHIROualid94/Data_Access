package com.sbip.da.repositories;


import com.sbip.da.models.Course;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.stream.Stream;

@Repository
public interface CourseRepository extends CrudRepository<Course,Long> {

    @Query("select c from Course c where c.category=?1") //JPQL query
    Iterable<Course> findAllByCategory(String category);

    @Query(value = "select * from COURSE c where c.category =?1 order by c.name desc ",nativeQuery = true) //Native query
    Iterable<Course> findAllByCategoryOrderByName(String category);

    @Query(value = "select * from COURSE c where c.category=?2 or c.name=?1", nativeQuery = true)//Native query
    Iterable<Course> findByNameOrCategory(String name,String category);

    @Query("select c from Course c where c.name like CONCAT('%',?1,'%') ") //JPQL query
    Iterable<Course> findByNameStartsWith(String name);

    @Modifying(clearAutomatically = true,flushAutomatically = true)
    @Query("update Course c set c.name=:name where c.id=:id")
    int updateCourseNameById(@Param("name") String name,@Param("id") Long id);
    Stream<Course> streamAllByCategory(String category);
    boolean existsByName(String name);
    long countByCategory(String category);


}
