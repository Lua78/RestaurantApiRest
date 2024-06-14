package com.api.demo.controllers;


import com.api.demo.repository.ReserveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reserves")
public class ReserveController {

    private final ReserveRepository reserveRepository;

    @Autowired
    public ReserveController(ReserveRepository reserveRepository) {
        this.reserveRepository = reserveRepository;
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
}
