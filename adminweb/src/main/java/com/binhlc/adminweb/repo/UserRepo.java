package com.binhlc.adminweb.repo;

import com.binhlc.adminweb.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<UserEntity,Integer> {
    @Override
    long count();

}
