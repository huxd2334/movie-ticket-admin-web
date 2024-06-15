package com.binhlc.adminweb.repo;

import com.binhlc.adminweb.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepo extends JpaRepository<MovieEntity, Integer>{

    @Override
    long count();

//    @Query(value = "SELECT * FROM movie_ticket.movie " +
//            "WHERE movie_ticket.movie.part_time < :part_date " +
//            "AND status = 1 limit :start,:limit  ", nativeQuery = true)
//    List<MovieEntity> getListMovieIsShowing(@Param("part_date")int part_date,
//                                            @Param("start")int start,
//                                            @Param("limit")int limit);
//
//    @Query(value = "SELECT * FROM movie_ticket.movie " +
//            "WHERE movie_ticket.movie.part_time > :part_date " +
//            "AND status = 1 limit :start,:limit ", nativeQuery = true)
//    List<MovieEntity> getListMovieCommingSoon(@Param("part_date")int part_date,
//                                            @Param("start")int start,
//                                            @Param("limit")int limit);
//    @Query(value = "SELECT  * FROM movie_ticket.movie " +
//            "ORDER BY movie_ticket.movie.view " +
//            "AND status = 1 desc limit 5" ,nativeQuery = true)
//    List<MovieEntity> getTopView();

}
