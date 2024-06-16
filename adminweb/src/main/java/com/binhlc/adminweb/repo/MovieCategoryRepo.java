package com.binhlc.adminweb.repo;

import com.binhlc.adminweb.entity.MovieCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface MovieCategoryRepo extends JpaRepository<MovieCategoryEntity, Integer> {
    List<MovieCategoryEntity> findAllByIdMovie(Integer idMovie);

    @Transactional
    void deleteAllByIdMovie(Integer idMovie);
}
