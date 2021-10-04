package com.treinamento.apostasquad.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.treinamento.apostasquad.Mensagem;
import com.treinamento.apostasquad.biz.TimePartidaBiz;
import com.treinamento.apostasquad.entities.TimePartida;
import com.treinamento.apostasquad.repositories.PartidaRepository;
import com.treinamento.apostasquad.repositories.TimePartidaRepository;
import com.treinamento.apostasquad.repositories.TimeRepository;

@RestController
@RequestMapping("timepartida")
@CrossOrigin
public class TimePartidaController {
	
	@Autowired
    TimePartidaRepository timePartidaRepository;
    
	@Autowired
    PartidaRepository partidaRepository;
    
	@Autowired
    TimeRepository timeRepository;
    
	@CrossOrigin
    @GetMapping("listar")
    public List<TimePartida> listarTimePartida() {
        List<TimePartida> lista = timePartidaRepository.findAll();
        return lista;
    }
    
	@CrossOrigin
    @PostMapping("incluir")
    public List<String> incluir(@Valid @RequestBody TimePartida novoTimePartida){
        Mensagem mensagem = new Mensagem();
        TimePartidaBiz validadorTimePartida = new TimePartidaBiz(partidaRepository, timeRepository);
        if(validadorTimePartida.validarTimePartida(novoTimePartida)){
            try{
                timePartidaRepository.save(novoTimePartida);
                timePartidaRepository.flush();
                mensagem.mensagem.add("Sucesso ao incluir timePartida!");

            }catch (Exception err){
                mensagem.mensagem.add("Erro ao incluir timePartida:");
                mensagem.mensagem.add(err.getMessage());
            }
        }else{
            mensagem.mensagem.addAll(validadorTimePartida.getMensagens().mensagem);
        }
        return mensagem.mensagem;
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public TimePartida consultar(@PathVariable int id) {
        return this.timePartidaRepository.findById(id).get();
    }

    @CrossOrigin
    @PutMapping("alterar")
    public Mensagem alterar(@Valid @RequestBody TimePartida novoTimePartida){
        TimePartidaBiz timePartidaBiz = new TimePartidaBiz(partidaRepository, timeRepository);
        try {
            if (timePartidaBiz.validarTimePartida(novoTimePartida)) {
                timePartidaRepository.save(novoTimePartida);
                timePartidaRepository.flush();
                timePartidaBiz.getMensagens().mensagem.add("TimePartida atualizada com sucesso!");
            } else {
                timePartidaBiz.getMensagens().mensagem.add("Erro ao alterar!");
            }
        } catch (Exception e) {
            timePartidaBiz.getMensagens().mensagem.add("Erro ao alterar: " + e.getMessage());
        }
        return timePartidaBiz.getMensagens();
    }
}
