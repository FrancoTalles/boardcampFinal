package com.boardcamp.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.boardcamp.api.dtos.GamesDTO;
import com.boardcamp.api.errors.GameAlreadyExistsException;
import com.boardcamp.api.models.GamesModel;
import com.boardcamp.api.repositories.GamesRepository;
import com.boardcamp.api.services.GamesService;

@SpringBootTest
class GamesUnitTests {

    @InjectMocks
    private GamesService gamesService;

    @Mock
    private GamesRepository gamesRepository;

    @SuppressWarnings("null")
    @Test
    void givenRepeatedGameName_whenCreatingGame_thenThrowsError() {
        GamesDTO gamesDto = new GamesDTO("Detetive", "link", 3, 1500);
        doReturn(true).when(gamesRepository).existsByName(any());

        GameAlreadyExistsException exception = assertThrows(
            GameAlreadyExistsException.class,
            () -> gamesService.save(gamesDto)
        );

        verify(gamesRepository, times(1)).existsByName(any());
        verify(gamesRepository, times(0)).save(any());
        assertNotNull(exception);
        assertEquals("Um jogo com esse nome já existe", exception.getMessage());
    }

    @SuppressWarnings("null")
    @Test
    void givenGameNameNotRepeated_whenCreatingGame_thenCreatesGame() {
        GamesDTO gameDto = new GamesDTO("Detetive", "link", 3, 1500);
        GamesModel newGame = new GamesModel(gameDto);

        doReturn(false).when(gamesRepository).existsByName(any());
        doReturn(newGame).when(gamesRepository).save(any());

        GamesModel result = gamesService.save(gameDto);

        assertNotNull(result);
        verify(gamesRepository, times(1)).existsByName(any());
        verify(gamesRepository, times(1)).save(any());
        assertEquals(newGame, result);
    }
}
