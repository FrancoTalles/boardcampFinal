package com.boardcamp.api.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.boardcamp.api.dtos.CustomersDTO;
import com.boardcamp.api.errors.CustomerAlreadyExistsException;
import com.boardcamp.api.errors.CustomerNotFoundException;
import com.boardcamp.api.models.CustomersModel;
import com.boardcamp.api.repositories.CustomersRepository;

import lombok.NonNull;

@Service
public class CustomersService {
    final CustomersRepository customersRepository;

    CustomersService(CustomersRepository customersRepository) {
        this.customersRepository = customersRepository;
    }

    public CustomersModel findById(@NonNull UUID id) {
        return customersRepository.findById(id).orElseThrow(
            () -> new CustomerNotFoundException("Cliente não encontrado com esse id!")
        );
    }

    public CustomersModel save(CustomersDTO dto) {
        if(!customersRepository.findByCpf(dto.getCpf()).isEmpty()) {
            throw new CustomerAlreadyExistsException("Já existe um cliente cadastrado com esse CPF");
        }

        CustomersModel customer = new CustomersModel(dto);
        return customersRepository.save(customer);
    }
}
