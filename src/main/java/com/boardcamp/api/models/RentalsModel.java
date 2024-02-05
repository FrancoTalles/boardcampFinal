package com.boardcamp.api.models;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import com.boardcamp.api.dtos.RentalsDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rentals")
public class RentalsModel {

    public RentalsModel(RentalsDTO dto, GamesModel game, CustomersModel customer) {
        this.rentDate = LocalDate.now();
        this.daysRented = dto.getDaysRented();
        this.returnDate = null;
        this.originalPrice = game.getPricePerDay() * dto.getDaysRented();
        this.delayFee = 0;
        this.game = game;
        this.customer = customer;
    }

    public RentalsModel(RentalsModel rental) {
        this.rentDate = rental.getRentDate();
        this.daysRented = rental.getDaysRented();
        this.returnDate = LocalDate.now();
        this.originalPrice = rental.getOriginalPrice();
        this.delayFee = Math.max(ChronoUnit.DAYS.between(this.rentDate, this.returnDate) - this.daysRented, 0) * (this.originalPrice / this.daysRented);
        this.game = rental.getGame();
        this.customer = rental.getCustomer();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private LocalDate rentDate;

    @Column(nullable = false)
    private int daysRented;

    @Column
    private LocalDate returnDate;

    @Column
    private double originalPrice;

    @Column
    private double delayFee;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private CustomersModel customer;

    @ManyToOne
    @JoinColumn(name = "gameId")
    private GamesModel game;
}
