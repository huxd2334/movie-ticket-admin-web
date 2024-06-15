package com.binhlc.adminweb.repo;

import com.binhlc.adminweb.entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepo extends JpaRepository<RoomEntity,Integer> {
    @Override
    long count();
}
