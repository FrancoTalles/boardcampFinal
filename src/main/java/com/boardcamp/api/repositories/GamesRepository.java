package com.boardcamp.api.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.boardcamp.api.models.GamesModel;

@Repository
public interface GamesRepository extends JpaRepository<GamesModel, UUID> {
    @Query(value = "SELECT CASE WHEN COUNT(*) > 0 THEN TRUE ELSE FALSE END FROM games WHERE name = :name", nativeQuery = true)
    boolean existsByName(@Param("name") String name);

}
