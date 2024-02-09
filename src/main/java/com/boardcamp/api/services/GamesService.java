package com.boardcamp.api.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.boardcamp.api.dtos.GamesDTO;
import com.boardcamp.api.errors.GameAlreadyExistsException;
import com.boardcamp.api.models.GamesModel;
import com.boardcamp.api.repositories.GamesRepository;

@Service
public class GamesService {
    final GamesRepository gamesRepository;

    GamesService(GamesRepository gamesRepository){
        this.gamesRepository = gamesRepository;
    }

    public List<GamesModel> findAll() {
        return gamesRepository.findAll();
    }

    public GamesModel save(GamesDTO dto) {
        GamesModel game = new GamesModel(dto);

        boolean exists = gamesRepository.existsByName(dto.getName());

        if(exists){
            throw new GameAlreadyExistsException("Um jogo com esse nome já existe");
        }

        return gamesRepository.save(game);
    }
}
