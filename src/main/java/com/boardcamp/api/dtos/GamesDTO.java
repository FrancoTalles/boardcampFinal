package com.boardcamp.api.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GamesDTO {

    @NotBlank
    @Size(max = 150, message = "O nome do jogo deve ser de no máximo 150 caracteres!")
    private String name;

    @NotBlank
    @Size(max = 300, message = "O tamanho máximo do link da imagem do jogo deve ser de 300 caracteres!")
    private String image;

    @NotNull
    @Min(1)
    private int stockTotal;

    @NotNull
    @Min(1)
    private double pricePerDay;
}
