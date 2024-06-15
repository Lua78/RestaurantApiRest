package com.api.demo.controllers;


import com.api.demo.model.Dinner;
import com.api.demo.repository.DinnerRepository;
import com.api.demo.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import java.util.List;

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
    @GetMapping
    public ResponseEntity<List<Dinner>> getAllDinners() {
        try {
            List<Dinner> dinners = dinnerRepo.findAll();
            return new ResponseEntity<>(dinners, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dinner> getDinnerById(@PathVariable("id") Long id) {
        Optional<Dinner> dinnerData = dinnerRepo.findById(id);
        return dinnerData.map(dinner -> new ResponseEntity<>(dinner, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateDinner(@PathVariable("id") Long id, @RequestBody Dinner dinner) {
        Optional<Dinner> dinnerData = dinnerRepo.findById(id);
        if (dinnerData.isPresent()) {
            String valString = Utils.validateObject(Dinner.class, dinner);
            if (!valString.equals("Ok")) {
                return new ResponseEntity<>(valString, HttpStatus.BAD_REQUEST);
            }
            Dinner _dinner = dinnerData.get();
            _dinner.setName(dinner.getName());
            _dinner.setDescription(dinner.getDescription());
            _dinner.setPrice(dinner.getPrice());
            return new ResponseEntity<>(dinnerRepo.save(_dinner), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Dinner> patchDinner(@PathVariable("id") Long id, @RequestBody Dinner dinner) {
        Optional<Dinner> dinnerData = dinnerRepo.findById(id);
        if (dinnerData.isPresent()) {
            Dinner _dinner = dinnerData.get();
            if (dinner.getName() != null) {
                _dinner.setName(dinner.getName());
            }
            if (dinner.getDescription() != null) {
                _dinner.setDescription(dinner.getDescription());
            }
            if (dinner.getPrice() != 0) {
                _dinner.setPrice(dinner.getPrice());
            }
            return new ResponseEntity<>(dinnerRepo.save(_dinner), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteDinner(@PathVariable("id") Long id) {
        try {
            dinnerRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
