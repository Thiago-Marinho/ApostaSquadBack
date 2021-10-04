package com.treinamento.apostasquad.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.treinamento.apostasquad.Mensagem;
import com.treinamento.apostasquad.biz.PartidaBiz;
import com.treinamento.apostasquad.entities.Partida;
import com.treinamento.apostasquad.repositories.EstadioRepository;
import com.treinamento.apostasquad.repositories.PartidaRepository;

@RestController
@RequestMapping("partida")
public class PartidaController {
	@Autowired
	private PartidaRepository partidaRepository;
	@Autowired
	private EstadioRepository estadioRepository;
	
	@GetMapping("listar")
	public List<Partida> listarPartidas(){
		return partidaRepository.findAll();
	}
	
	@GetMapping(path = {"listar/{id}"})
	public Partida listarPartida(@PathVariable int id){
		return partidaRepository.findById(id).get();
	}
	
	@PostMapping("incluir")
	public Mensagem incluirPartida(@Validated @RequestBody Partida partida) {
		PartidaBiz partidaBiz = new PartidaBiz(estadioRepository);
		try {
			if(partidaBiz.validarPartida(partida)) {
				partidaRepository.save(partida);
				partidaRepository.flush();
				partidaBiz.getMensagem().mensagem.add("Ok!");
			}	
		} catch (Exception e) {
			partidaBiz.getMensagem().mensagem.add("Erro ao incluir: " + e.getMessage());
		}
		return partidaBiz.getMensagem();
	}
	
	@PutMapping("alterar")
	public Mensagem alterarPartida(@Validated @RequestBody Partida partida) {
		PartidaBiz partidaBiz = new PartidaBiz(estadioRepository);
		try {
			if(partidaBiz.validarPartida(partida) && !partidaRepository.findById(partida.getId()).isEmpty()) {
				partidaRepository.save(partida);
				partidaRepository.flush();
				partidaBiz.getMensagem().mensagem.add("Ok!");
			}else {
				partidaBiz.getMensagem().mensagem.add("Id mencionado não existe!");
			}
		} catch (Exception e) {
			partidaBiz.getMensagem().mensagem.add("Erro ao alterar: " + e.getMessage());
		}
		return partidaBiz.getMensagem();
	} 
}
