package com.treinamento.apostasquad.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.treinamento.apostasquad.entities.Situacao;

@Repository
public interface SituacaoRepository extends JpaRepository<Situacao, Integer>{

}
