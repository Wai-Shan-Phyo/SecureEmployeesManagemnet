package com.sem.project.position.repository;

import com.sem.project.position.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PositionRepository extends JpaRepository<Position,Long> {
    Optional<Position>  findByPositionName(String position);
    boolean existsByPositionName(String positionName);
}
