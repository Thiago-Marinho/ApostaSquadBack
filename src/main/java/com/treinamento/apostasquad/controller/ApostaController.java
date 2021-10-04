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
import com.treinamento.apostasquad.biz.ApostaBiz;
import com.treinamento.apostasquad.entities.Aposta;
import com.treinamento.apostasquad.repositories.ApostaRepository;
import com.treinamento.apostasquad.repositories.ClienteRepository;
import com.treinamento.apostasquad.repositories.SituacaoRepository;

@RestController
@RequestMapping("aposta")
@CrossOrigin
public class ApostaController {
	@Autowired
    ApostaRepository apostaRepository;
    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    SituacaoRepository situacaoRepository;
    @CrossOrigin
    @GetMapping("listar")
    public List<Aposta> listarAposta() {
        List<Aposta> lista = apostaRepository.findAll();
        return lista;
    }
    @CrossOrigin
    @PostMapping("incluir")
    public List<String> incluir(@Valid @RequestBody Aposta novoAposta){
        Mensagem mensagem = new Mensagem();
        ApostaBiz validadorAposta = new ApostaBiz(clienteRepository, situacaoRepository );
        if(validadorAposta.validarAposta(novoAposta)){
            try{
                apostaRepository.save(novoAposta);
                apostaRepository.flush();
                mensagem.mensagem.add("Sucesso ao incluir aposta!");

            }catch (Exception err){
                mensagem.mensagem.add("Erro ao incluir aposta:");
                mensagem.mensagem.add(err.getMessage());
            }
        }else{
            mensagem.mensagem.addAll(validadorAposta.getMensagens().mensagem);
        }
        return mensagem.mensagem;
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public Aposta consultar(@PathVariable int id) {
        return this.apostaRepository.findById(id).get();
    }

    @CrossOrigin
    @PutMapping("alterar")
    public Mensagem alterar(@Valid @RequestBody Aposta novoAposta){
        ApostaBiz apostaBiz = new ApostaBiz(clienteRepository, situacaoRepository);
        try {
            if (apostaBiz.validarAposta(novoAposta)) {
                apostaRepository.save(novoAposta);
                apostaRepository.flush();
                apostaBiz.getMensagens().mensagem.add("CorridaClientePiloto atualizado com sucesso!");
            } else {
                apostaBiz.getMensagens().mensagem.add("Erro ao alterar!");
            }
        } catch (Exception e) {
            apostaBiz.getMensagens().mensagem.add("Erro ao alterar: " + e.getMessage());
        }
        return apostaBiz.getMensagens();
    }
}
