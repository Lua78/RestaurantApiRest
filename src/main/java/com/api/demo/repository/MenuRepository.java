package com.api.demo.repository;

import com.api.demo.model.Dinner;
import com.api.demo.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    @Transactional
    @Query("SELECT d FROM Dinner d INNER JOIN MenuDinner md ON md.id_dinner = d.id_dinner WHERE md.id_menu = :menu")
    List<Dinner> getDinners(long menu);
}
