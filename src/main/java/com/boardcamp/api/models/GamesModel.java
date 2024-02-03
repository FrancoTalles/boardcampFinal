package com.boardcamp.api.models;

import java.util.UUID;

import com.boardcamp.api.dtos.GamesDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "games")
public class GamesModel {

    public GamesModel(GamesDTO dto) {
        this.name = dto.getName();
        this.image = dto.getImage();
        this.stockTotal = dto.getStockTotal();
        this.pricePerDay = dto.getPricePerDay();
    }

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
