package com.boardcamp.api.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.boardcamp.api.models.GamesModel;
import java.util.List;


@Repository
public interface GamesRepository extends JpaRepository<GamesModel, UUID> {
    List<GamesModel> findByName(String name);
}
