package com.treinamento.apostasquad.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.treinamento.apostasquad.entities.Aposta;

@Repository
public interface ApostaRepository extends JpaRepository<Aposta, Integer>{

}
