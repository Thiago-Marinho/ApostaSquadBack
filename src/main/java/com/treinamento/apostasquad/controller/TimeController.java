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
import com.treinamento.apostasquad.biz.TimeBiz;
import com.treinamento.apostasquad.entities.Time;
import com.treinamento.apostasquad.repositories.TimeRepository;

@RestController
@RequestMapping("time")
@CrossOrigin
public class TimeController {
	
	@Autowired
	private TimeRepository timeRepository;
	
	@GetMapping("listar")
	public List<Time> listarTime(){
		List<Time> lista = timeRepository.findAll();
		return lista;
	}
	
	@CrossOrigin
	@GetMapping("/{id}")
    public Time consultar(@PathVariable Integer id) {
        return timeRepository.findById(id).get();
    }
	
	@PostMapping("incluir")
	public Mensagem incluirTime(@Validated @RequestBody Time time) {
		
		TimeBiz timeBiz = new TimeBiz();
		try {
			
			if (timeBiz.validar(time)) {
				timeRepository.save(time);
				timeRepository.flush();
				timeBiz.getMsg().getMensagem().add("OK");
			}
			
		} catch (Exception e) {
			timeBiz.getMsg().getMensagem().add("Erro ao Incluir:" + e.getMessage());
		}
		return timeBiz.getMsg();
	}
	
	@CrossOrigin
	@PutMapping("alterar")
    public Mensagem alterarTime(@RequestBody @Validated Time time) {

		TimeBiz timeBiz = new TimeBiz();
		try {
			if(timeBiz.validar(time) && !timeRepository.findById(time.getId()).isEmpty()) {
				timeRepository.save(time);
				timeRepository.flush();
				timeBiz.getMsg().mensagem.add("Ok!");
			}else {
				timeBiz.getMsg().mensagem.add("Id mencionado não existe!");
			}
		} catch (Exception e) {
			timeBiz.getMsg().mensagem.add("Erro ao alterar: " + e.getMessage());
		}
		return timeBiz.getMsg();
	}

}





