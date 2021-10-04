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
import com.treinamento.apostasquad.biz.ResultadoApostaBiz;
import com.treinamento.apostasquad.entities.ResultadoAposta;
import com.treinamento.apostasquad.repositories.ApostaRepository;
import com.treinamento.apostasquad.repositories.ResultadoApostaRepository;
import com.treinamento.apostasquad.repositories.TimePartidaRepository;

@RestController
@RequestMapping("resultadoaposta")
@CrossOrigin
public class ResultadoApostaController {
	@Autowired
	private ResultadoApostaRepository resultadoApostaRepository;
	@Autowired
	private ApostaRepository apostaRepository;
	@Autowired
	private TimePartidaRepository timePartidaRepository;
	
	@GetMapping("listar")
	public List<ResultadoAposta> listarResultadoApostas(){
		return resultadoApostaRepository.findAll();
	}
	
	@GetMapping(path = {"listar/{id}"})
	public ResultadoAposta listarResultadoAposta(@PathVariable int id){
		return resultadoApostaRepository.findById(id).get();
	}
	
	@PostMapping("incluir")
	public Mensagem incluirResultadoAposta(@Validated @RequestBody ResultadoAposta resultadoAposta) {
		ResultadoApostaBiz resultadoApostaBiz = new ResultadoApostaBiz(apostaRepository, timePartidaRepository);
		try {
			if(resultadoApostaBiz.validarResultadoAposta(resultadoAposta)) {
				resultadoApostaRepository.save(resultadoAposta);
				resultadoApostaRepository.flush();
				resultadoApostaBiz.getMensagem().mensagem.add("Ok!");
			}	
		} catch (Exception e) {
			resultadoApostaBiz.getMensagem().mensagem.add("Erro ao incluir: " + e.getMessage());
		}
		return resultadoApostaBiz.getMensagem();
	}
	
	@PutMapping("alterar")
	public Mensagem alterarResultadoAposta(@Validated @RequestBody ResultadoAposta resultadoAposta) {
		ResultadoApostaBiz resultadoApostaBiz = new ResultadoApostaBiz(apostaRepository, timePartidaRepository);
		try {
			if(resultadoApostaBiz.validarResultadoAposta(resultadoAposta) && !resultadoApostaRepository.findById(resultadoAposta.getId()).isEmpty()) {
				resultadoApostaRepository.save(resultadoAposta);
				resultadoApostaRepository.flush();
				resultadoApostaBiz.getMensagem().mensagem.add("Ok!");
			}else {
				resultadoApostaBiz.getMensagem().mensagem.add("Id mencionado não existe!");
			}
		} catch (Exception e) {
			resultadoApostaBiz.getMensagem().mensagem.add("Erro ao alterar: " + e.getMessage());
		}
		return resultadoApostaBiz.getMensagem();
	} 
}
