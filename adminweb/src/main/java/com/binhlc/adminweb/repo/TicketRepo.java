package com.binhlc.adminweb.repo;

import com.binhlc.adminweb.entity.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepo extends JpaRepository<TicketEntity,Integer> {
    @Query(value = "SELECT * FROM ticket " +
            "WHERE id_movie_time = :idMovie " +
            "ORDER BY id_room_detail asc",nativeQuery = true)
    List<TicketEntity> getListTicket(@Param("idMovie") Integer idMovie);

    boolean existsByIdMovieTime(Integer idMovieTime);
    List<TicketEntity> findAllByIdMovieTime(Integer idMovieTime);
    List<TicketEntity> findAllByIdMovie(Integer idMovie);
}
