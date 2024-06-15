package com.binhlc.adminweb.repo;

import com.binhlc.adminweb.entity.RoomDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomDetailRepo extends JpaRepository<RoomDetailEntity,Integer> {
    List<RoomDetailEntity> findAllByRoom(int room);
}
