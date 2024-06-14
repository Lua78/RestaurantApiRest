package com.api.demo.repository;

import com.api.demo.model.Reserve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ReserveRepository extends JpaRepository<Reserve, Long> {
    @Modifying
    @Transactional
    @Query("UPDATE Reserve r SET r.state = false WHERE r.id_reserve = :reserveId")
    void cancel(@Param("reserveId") Long reserveId);
}
