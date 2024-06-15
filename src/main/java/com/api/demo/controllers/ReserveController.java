package com.api.demo.controllers;


import com.api.demo.model.Reserve;
import com.api.demo.repository.ReserveRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.api.demo.util.Utils;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;


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
}
