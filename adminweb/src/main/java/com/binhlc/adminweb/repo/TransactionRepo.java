package com.binhlc.adminweb.repo;

import com.binhlc.adminweb.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepo extends JpaRepository<TransactionEntity,Integer> {

    @Query(value = "")
    List<TransactionEntity> findAllByType(int type);  //type = 2 là giao dịch thành công

    @Query(value = "")
    List<TransactionEntity> findAllByIdUserAndTypeOrderByTimeDesc(Integer idUser, int type);
}
