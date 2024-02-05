package com.boardcamp.api.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.boardcamp.api.models.RentalsModel;

@Repository
public interface RentalsRepository extends JpaRepository<RentalsModel, UUID>{

    @Query(value="SELECT * FROM rentals WHERE return_date IS null AND game_id = :gameId", nativeQuery = true)
    List<RentalsModel> findOpenRentals(@Param("gameId") UUID gameId);   

    @Query(value = "SELECT CASE WHEN COUNT(*) > 0 THEN TRUE ELSE FALSE END FROM rentals WHERE customer_id = :customerId AND game_id = :gameId", nativeQuery = true)
    boolean findSameRentals(@Param("customerId") UUID customerId, @Param("gameId") UUID gameId);
}
