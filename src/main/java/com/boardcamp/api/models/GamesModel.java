package com.boardcamp.api.models;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GamesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(length = 150, nullable = false, unique = true)
    private String name;

    @Column(length = 300, nullable = false)
    private String image;

    @Column(nullable = false)
    private int stockTotal;

    @Column(nullable = false)
    private double pricePerDay;
}
