package com.treinamento.apostasquad.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.treinamento.apostasquad.entities.ResultadoAposta;

@Repository
public interface ResultadoApostaRepository extends JpaRepository<ResultadoAposta, Integer>{

}
