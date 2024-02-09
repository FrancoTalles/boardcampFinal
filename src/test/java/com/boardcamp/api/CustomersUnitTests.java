package com.boardcamp.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.boardcamp.api.dtos.CustomersDTO;
import com.boardcamp.api.errors.CustomerNotFoundException;
import com.boardcamp.api.models.CustomersModel;
import com.boardcamp.api.repositories.CustomersRepository;
import com.boardcamp.api.services.CustomersService;

@SpringBootTest
class CustomersUnitTests {
    
    @InjectMocks
    private CustomersService customersService;

    @Mock
    private CustomersRepository customersRepository;

    @SuppressWarnings("null")
    @Test
    void GivenCustomerIdNotExists_whenFindCustomer_thenThrowsError() {
        UUID customerId = UUID.randomUUID();

        doReturn(Optional.empty()).when(customersRepository).findById(customerId);

        CustomerNotFoundException exception = assertThrows(
            CustomerNotFoundException.class,
            () -> customersService.findById(customerId)
        );

        verify(customersRepository, times(1)).findById(customerId);
        assertNotNull(exception);
        assertEquals("Cliente não encontrado com esse id!", exception.getMessage());
    }

    @SuppressWarnings("null")
    @Test
    void GivenCustomerUniqueCpf_whenCreatingCustomer_thenCreatesCustomer() {
        CustomersDTO customerDto = new CustomersDTO("Paulo", "12345678910");
        CustomersModel newCustomer = new CustomersModel(customerDto);

        doReturn(Collections.emptyList()).when(customersRepository).findByCpf(any());
        doReturn(newCustomer).when(customersRepository).save(any());

        CustomersModel result = customersService.save(customerDto);

        assertNotNull(result);
        verify(customersRepository, times(1)).findByCpf(customerDto.getCpf());
        verify(customersRepository, times(1)).save(any());
        assertEquals(newCustomer, result);
    }
}
