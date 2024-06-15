package com.binhlc.adminweb.repo;

import com.binhlc.adminweb.entity.UserAdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAdminRepo extends JpaRepository<UserAdminEntity, Integer> {
    UserAdminEntity findByUsername(String username);
}
