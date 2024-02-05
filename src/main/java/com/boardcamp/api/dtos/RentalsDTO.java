package com.boardcamp.api.dtos;

import java.util.UUID;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RentalsDTO {

    @NotNull
    private UUID customerId;

    @NotNull
    private UUID gameId;

    @NotNull
    @Min(1)
    private int daysRented;
}
