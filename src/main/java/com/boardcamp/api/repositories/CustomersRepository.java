package com.boardcamp.api.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.boardcamp.api.models.CustomersModel;
import java.util.List;


@Repository
public interface CustomersRepository extends JpaRepository<CustomersModel, UUID>{
    List<CustomersModel> findByCpf(String cpf);
}
