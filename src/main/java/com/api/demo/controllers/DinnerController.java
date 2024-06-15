package com.api.demo.controllers;


import com.api.demo.model.Dinner;
import com.api.demo.model.Menu;
import com.api.demo.repository.DinnerRepository;
import com.api.demo.repository.MenuRepository;
import com.api.demo.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dinners")
public class DinnerController {
    private final DinnerRepository dinnerRepo;
    @Autowired
    public DinnerController(DinnerRepository dinnerRepo) {
        this.dinnerRepo = dinnerRepo;
    }


    @PostMapping
    public ResponseEntity<String> createDinner(@RequestBody Dinner dinner) {
        String valString = Utils.validateObject(Dinner.class, dinner);
        if (!valString.equals("Ok")) {
            return new ResponseEntity<>(valString, HttpStatus.BAD_REQUEST);
        }
        try {
            dinnerRepo.save(dinner);
            return new ResponseEntity<>("Plato creado exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la plato: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
