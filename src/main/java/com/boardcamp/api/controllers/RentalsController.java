package com.boardcamp.api.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boardcamp.api.dtos.RentalsDTO;
import com.boardcamp.api.models.RentalsModel;
import com.boardcamp.api.services.RentalsService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/rentals")
public class RentalsController {
    
    final RentalsService rentalsService;

    public RentalsController(RentalsService rentalsService){
        this.rentalsService = rentalsService;
    }

    @GetMapping
    public ResponseEntity<List<RentalsModel>> getRentals(){
        List<RentalsModel> rentals = rentalsService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(rentals);
    }

    @PostMapping
    public ResponseEntity<RentalsModel> createRental(@RequestBody @Valid RentalsDTO body) {
        RentalsModel rental = rentalsService.save(body);
        return ResponseEntity.status(HttpStatus.OK).body(rental);
    }

    @PutMapping("/{id}/return")
    public ResponseEntity<RentalsModel> finishRental(@PathVariable UUID id) {
        RentalsModel rental = rentalsService.finishRental(id);
        return ResponseEntity.status(HttpStatus.OK).body(rental);
    }
}
