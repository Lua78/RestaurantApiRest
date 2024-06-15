package com.api.demo.controllers;

import com.api.demo.model.Dinner;
import com.api.demo.model.Menu;
import com.api.demo.model.Reserve;
import com.api.demo.repository.MenuRepository;
import com.api.demo.repository.ReserveRepository;
import com.api.demo.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.AbstractPersistable_;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@RestController
@RequestMapping("/menus")
public class MenuController {
    private final MenuRepository menuRepo;
    @Autowired
    public MenuController(MenuRepository menuRepo) {
        this.menuRepo = menuRepo;
    }

    @GetMapping("dinners/{idMenu}")
    public ResponseEntity<List<Dinner>> getDinners(@PathVariable long idMenu){
        try {
            List<Dinner> dinners = menuRepo.getDinners(idMenu);
            return new ResponseEntity<>(dinners, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping
    public ResponseEntity<String> createMenu(@RequestBody Menu menu) {
        String valString = Utils.validateObject(Menu.class, menu);
        if (!valString.equals("Ok")) {
            return new ResponseEntity<>(valString, HttpStatus.BAD_REQUEST);
        }
        try {
            menuRepo.save(menu);
            return new ResponseEntity<>("Menu creado exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la menu: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
