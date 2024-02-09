package com.boardcamp.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.boardcamp.api.dtos.CustomersDTO;
import com.boardcamp.api.models.CustomersModel;
import com.boardcamp.api.repositories.CustomersRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class CustomerIntegrationTests {
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CustomersRepository customersRepository;

    @BeforeEach
    @AfterEach
    public void cleanUpDatabase() {
        customersRepository.deleteAll();
    }

    @Test
    void givenCustomerCpfAlreadyExists_whenCreateCustomer_thenThrowsError() {
        UUID customerId = UUID.randomUUID();


        CustomersDTO customer = new CustomersDTO("Paulo", "12345678910");
        CustomersModel customerConflict = new CustomersModel(customerId, "Paulo", "12345678910");
        customersRepository.save(customerConflict);

        HttpEntity<CustomersDTO> body = new HttpEntity<>(customer);

        ResponseEntity<String> response = restTemplate.exchange(
            "/customers",
            HttpMethod.POST,
            body,
            String.class
        );

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals(1, customersRepository.count());
    }
}
