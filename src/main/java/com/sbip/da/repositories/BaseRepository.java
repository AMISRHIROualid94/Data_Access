package com.sbip.da.repositories;

import com.sbip.da.models.Course;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.util.ArrayList;
import java.util.List;

@NoRepositoryBean
//Spring can't create an instance from this BaseRepository
// exclude it from proxy object creation
public interface BaseRepository<T,ID> extends Repository<T,ID> {
    <S extends T> S save(S entity);
    Iterable<T> findAll();
}
