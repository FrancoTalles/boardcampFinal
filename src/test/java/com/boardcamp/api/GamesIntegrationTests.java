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

import com.boardcamp.api.dtos.GamesDTO;
import com.boardcamp.api.models.GamesModel;
import com.boardcamp.api.repositories.GamesRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class GamesIntegrationTests {
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private GamesRepository gamesRepository;

    @BeforeEach
    @AfterEach
    public void cleanUpDatabase() {
        gamesRepository.deleteAll();
    }

    @Test
    void givenRepeatedGameName_whenCreatingGame_thenThrowsError() {
        UUID gameId = UUID.randomUUID();
        
        GamesDTO game = new GamesDTO("Detetive", "Link", 3, 1500);
        GamesModel gameConflict = new GamesModel(gameId, "Detetive", "Link", 3, 1500);
        gamesRepository.save(gameConflict);

        HttpEntity<GamesDTO> body = new HttpEntity<>(game);

        ResponseEntity<String> response = restTemplate.exchange(
            "/games",
            HttpMethod.POST,
            body,
            String.class
        );

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals(1, gamesRepository.count());
    }

    @Test
    void givenGameNameNotRepeated_whenCreatingGame_thenCreatesGame() {
        GamesDTO game = new GamesDTO("Jogo da Vida", "Link", 10, 1000);
        GamesModel gameConflict = new GamesModel(null, "Detetive", "Link", 3, 1500);
        gamesRepository.save(gameConflict);

        HttpEntity<GamesDTO> body = new HttpEntity<>(game);
        
        ResponseEntity<String> response = restTemplate.exchange(
            "/games",
            HttpMethod.POST,
            body,
            String.class
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, gamesRepository.count());
    }
}
