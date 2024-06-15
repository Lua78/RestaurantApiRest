package com.api.demo.controllers;


import com.api.demo.model.Reserve;
import com.api.demo.repository.ReserveRepository;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.api.demo.util.Utils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/reserves")
public class ReserveController {

    private final ReserveRepository reserveRepository;
    private final Validator validator;

    @Autowired
    public ReserveController(ReserveRepository reserveRepository, Validator validator) {
        this.reserveRepository = reserveRepository;
        this.validator = validator;
    }
    @PostMapping("/cancel/")
    public ResponseEntity<String> cancelReserve(@RequestParam("reserveId") Long reserveId) {
        try {
            reserveRepository.cancel(reserveId);
            return ResponseEntity.ok("Reserva cancelada exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al cancelar la reserva: " + e.getMessage());
        }
    }
    @PostMapping
    public ResponseEntity<String> createReserve(@RequestBody Reserve reserve) {
        String valString = Utils.validateObject(Reserve.class, reserve);
        if (valString.equals("Ok")) {
            try {
                reserveRepository.save(reserve);
                return new ResponseEntity<>("Reserva creada exitosamente", HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>("Error al crear la reserva: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<>(valString, HttpStatus.BAD_REQUEST);
    }
    @GetMapping
    public ResponseEntity<List<Reserve>> getAllReserves() {
        try {
            List<Reserve> reserves = reserveRepository.findAll();
            return new ResponseEntity<>(reserves, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reserve> getReserveById(@PathVariable("id") Long id) {
        Optional<Reserve> reserveDate = reserveRepository.findById(id);
        return reserveDate.map(reserve -> new ResponseEntity<>(reserve, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateReserve(@PathVariable("id") Long id, @RequestBody Reserve reserve) {
        Optional<Reserve> reserveData = reserveRepository.findById(id);
        if (reserveData.isPresent()) {
            String valString = Utils.validateObject(Reserve.class, reserve);
            if (!valString.equals("Ok")) {
                return new ResponseEntity<>(valString, HttpStatus.BAD_REQUEST);
            }
            Reserve _reserve = reserveData.get();
            _reserve.setCostumer_number(reserve.getCostumer_number());
            _reserve.setState(reserve.getState());
            _reserve.setDate_reserve(reserve.getDate_reserve());
            _reserve.setId_menu(reserve.getId_menu());
            return new ResponseEntity<>(reserveRepository.save(_reserve), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Reserve> patchReserve(@PathVariable("id") Long id, @RequestBody Reserve reserve) {
        Optional<Reserve> reserveDate = reserveRepository.findById(id);
        if (reserveDate.isPresent()) {
            Reserve _reserve = reserveDate.get();
            if (reserve.getCostumer_number() > 0) {
                _reserve.setCostumer_number(reserve.getCostumer_number());
            }
            if (reserve.getState()) {
                _reserve.setState(reserve.getState());
            }
            if (reserve.getState()) {
                _reserve.setState(reserve.getState());
            }
            if (reserve.getId_menu()>0) {
                _reserve.setId_menu(reserve.getId_menu());
            }
            return new ResponseEntity<>(reserveRepository.save(_reserve), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteReserve(@PathVariable("id") Long id) {
        try {
            reserveRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
