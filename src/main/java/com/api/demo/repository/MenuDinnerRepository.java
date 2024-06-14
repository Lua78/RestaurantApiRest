package com.api.demo.repository;

import com.api.demo.model.MenuDinnerRelation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuDinnerRepository extends JpaRepository<MenuDinnerRelation, Long> {

}
