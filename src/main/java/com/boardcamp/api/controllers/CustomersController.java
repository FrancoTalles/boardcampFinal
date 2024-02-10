package com.boardcamp.api.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boardcamp.api.dtos.CustomersDTO;
import com.boardcamp.api.models.CustomersModel;
import com.boardcamp.api.services.CustomersService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/customers")
public class CustomersController {
    
    final CustomersService customersService;

    public CustomersController(CustomersService customersService) {
        this.customersService = customersService;
    }

    @GetMapping
    public ResponseEntity<List<CustomersModel>> getAllCustomers() {
        List<CustomersModel> customers = customersService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(customers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomersModel> getCustomerById(@PathVariable UUID id) {
        CustomersModel customer = customersService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(customer);
    }

    @PostMapping
    public ResponseEntity<CustomersModel> createCustomers(@RequestBody @Valid CustomersDTO body){
        CustomersModel customer = customersService.save(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(customer);
    }
}
