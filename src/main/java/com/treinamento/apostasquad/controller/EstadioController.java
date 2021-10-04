package com.treinamento.apostasquad.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.treinamento.apostasquad.Mensagem;
import com.treinamento.apostasquad.biz.EstadioBiz;
import com.treinamento.apostasquad.entities.Estadio;
import com.treinamento.apostasquad.repositories.EstadioRepository;
@RestController
@RequestMapping("estadio")
@CrossOrigin
public class EstadioController {
	@Autowired
	private EstadioRepository estadioRepository;
	
	public EstadioController() {
	}
	
	@GetMapping("listar")
	public List<Estadio> listarEstadios(){
		return estadioRepository.findAll();
	}
	
	@GetMapping(path = {"listar/{id}"})
	public Estadio listarEstadio(@PathVariable int id){
		return estadioRepository.findById(id).get();
	}
	
	@PostMapping("incluir")
	public Mensagem incluirEstadio(@Validated @RequestBody Estadio estadio) {
		EstadioBiz estadioBiz = new EstadioBiz();
		try {
			if(estadioBiz.validar(estadio)) {
				estadioRepository.save(estadio);
				estadioRepository.flush();
				estadioBiz.getMensagem().mensagem.add("Ok!");
			}	
		} catch (Exception e) {
			estadioBiz.getMensagem().mensagem.add("Erro ao incluir: " + e.getMessage());
		}
		return estadioBiz.getMensagem();
	}
	
	@PutMapping("alterar")
	public Mensagem alterarEstadio(@Validated @RequestBody Estadio estadio) {
		EstadioBiz estadioBiz = new EstadioBiz();
		try {
			if(estadioBiz.validar(estadio) && !estadioRepository.findById(estadio.getId()).isEmpty()) {
				estadioRepository.save(estadio);
				estadioRepository.flush();
				estadioBiz.getMensagem().mensagem.add("Ok!");
			}else {
				estadioBiz.getMensagem().mensagem.add("Id mencionado não existe!");
			}
		} catch (Exception e) {
			estadioBiz.getMensagem().mensagem.add("Erro ao alterar: " + e.getMessage());
		}
		return estadioBiz.getMensagem();
	}
}
