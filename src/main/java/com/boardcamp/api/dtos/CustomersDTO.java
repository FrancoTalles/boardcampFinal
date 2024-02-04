package com.boardcamp.api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomersDTO {

    @NotBlank
    @Size(max = 150, message = "O nome do cliente deve ter no máximo 150 caracteres!")
    private String name;

    @NotBlank
    @Size(min = 11, max = 11, message = "O cpf deve ter 11 digitos!")
    private String cpf;
}
