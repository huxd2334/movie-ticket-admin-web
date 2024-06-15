package com.binhlc.adminweb.repo;

import com.binhlc.adminweb.entity.DetailTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailTransactionRepo extends JpaRepository<DetailTransactionEntity, Integer>{

    @Override
    long count();

    List<DetailTransactionEntity> findAllByIdTransaction(int idTrans);
}
