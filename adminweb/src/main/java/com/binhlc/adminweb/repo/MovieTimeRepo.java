package com.binhlc.adminweb.repo;

import com.binhlc.adminweb.entity.MovieTimeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieTimeRepo extends JpaRepository<MovieTimeEntity,Integer> {
    boolean existsByDateStartAndTimeStart(Integer dateStart, Integer timeStart);
    @Query(value = "")
    List<MovieTimeEntity> findAllByIdMovie(int idMovie);
}
