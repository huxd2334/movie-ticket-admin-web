package com.binhlc.adminweb.repo;

import com.binhlc.adminweb.entity.MovieCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieCategoryRepo extends JpaRepository<MovieCategoryEntity, Integer> {
    List<MovieCategoryRepo> findAllByIdMovie(Integer idMovie);
}
