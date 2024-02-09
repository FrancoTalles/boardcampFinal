package com.boardcamp.api.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.boardcamp.api.dtos.RentalsDTO;
import com.boardcamp.api.errors.CustomerNotFoundException;
import com.boardcamp.api.errors.GameAlreadyRentedException;
import com.boardcamp.api.errors.GameIsSoldOutException;
import com.boardcamp.api.errors.GameNotFoundException;
import com.boardcamp.api.errors.RentalAlreadyFinishException;
import com.boardcamp.api.errors.RentalNotFoundException;
import com.boardcamp.api.models.CustomersModel;
import com.boardcamp.api.models.GamesModel;
import com.boardcamp.api.models.RentalsModel;
import com.boardcamp.api.repositories.CustomersRepository;
import com.boardcamp.api.repositories.GamesRepository;
import com.boardcamp.api.repositories.RentalsRepository;

@Service
public class RentalsService {
    final RentalsRepository rentalsRepository;
    final GamesRepository gamesRepository;
    final CustomersRepository customersRepository;

    RentalsService(RentalsRepository rentalsRepository, GamesRepository gamesRepository, CustomersRepository customersRepository){
        this.rentalsRepository = rentalsRepository;
        this.gamesRepository = gamesRepository;
        this.customersRepository = customersRepository;
    }

    public List<RentalsModel> findAll(){
        return rentalsRepository.findAll();
    }

    public RentalsModel save(RentalsDTO dto) {

        @SuppressWarnings("null")
        GamesModel game = gamesRepository.findById(dto.getGameId()).orElseThrow(
            () -> new GameNotFoundException("Não existe o jogo informado em nosso banco de dados!")
        );

        @SuppressWarnings("null")
        CustomersModel customer = customersRepository.findById(dto.getCustomerId()).orElseThrow(
            () -> new CustomerNotFoundException("Não existe o cliente informado em nosso banco de dados!")
        );

        boolean rentalExist = rentalsRepository.findSameRentals(dto.getCustomerId(), dto.getGameId());

        if(rentalExist){
            throw new GameAlreadyRentedException("O jogo já foi alugado pelo cliente!");
        }

        List<RentalsModel> rentals = rentalsRepository.findOpenRentals(dto.getGameId());

        if(rentals.size() >= game.getStockTotal()){
            throw new GameIsSoldOutException("O jogo está esgotado!");
        }

        RentalsModel rental = new RentalsModel(dto, game, customer);

        return rentalsRepository.save(rental);
    }

    public RentalsModel finishRental(UUID id) {
        
        @SuppressWarnings("null")
        RentalsModel rental = rentalsRepository.findById(id).orElseThrow(
            () -> new RentalNotFoundException("Não existe o aluguel informado!")
        );

        if(rental.getReturnDate() != null) {
            throw new RentalAlreadyFinishException("Esse aluguel já foi finalizado!");
        }

        RentalsModel newRental = new RentalsModel(rental);
        newRental.setId(id);
        return rentalsRepository.save(newRental);
    }
}
