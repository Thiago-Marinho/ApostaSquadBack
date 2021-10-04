package com.treinamento.apostasquad.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.treinamento.apostasquad.entities.Partida;

@Repository
public interface PartidaRepository extends JpaRepository<Partida, Integer>{

}
