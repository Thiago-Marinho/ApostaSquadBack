package com.treinamento.apostasquad.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.treinamento.apostasquad.entities.Time;

@Repository
public interface TimeRepository extends JpaRepository<Time, Integer> {

}
