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

import com.treinamento.apostasquad.Mensagem;
import com.treinamento.apostasquad.entities.Aposta;
import com.treinamento.apostasquad.repositories.ApostaRepository;
import com.treinamento.apostasquad.repositories.ClienteRepository;
import com.treinamento.apostasquad.repositories.SituacaoRepository;

public class ApostaController {
	@Autowired
    ApostaRepository servicoRepositorio;
    @Autowired
    ClienteRepository carroRepositorio;
    @Autowired
    SituacaoRepository mecanicoRepository;
    @CrossOrigin
    @GetMapping("listar")
    public List<Aposta> listarAposta() {
        List<Aposta> lista = servicoRepositorio.findAll();
        return lista;
    }
    @CrossOrigin
    @PostMapping("incluir")
    public List<String> incluir(@Valid @RequestBody Aposta novoAposta){
        Mensagem mensagem = new Mensagem();
        ApostaBiz validadorAposta = new ApostaBiz(carroRepositorio);
        if(validadorAposta.validarAposta(novoAposta)){
            try{
                servicoRepositorio.save(novoAposta);
                servicoRepositorio.flush();
                mensagem.mensagem.add("Sucesso ao incluir servico!");

            }catch (Exception err){
                mensagem.mensagem.add("Erro ao incluir servico:");
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
        return this.servicoRepositorio.findById(id).get();
    }

    @CrossOrigin
    @PutMapping("alterar")
    public Mensagem alterar(@Valid @RequestBody Aposta novoAposta){
        ApostaBiz servicoBiz = new ApostaBiz(carroRepositorio);
        try {
            if (servicoBiz.validarAposta(novoAposta)) {
                servicoRepositorio.save(novoAposta);
                servicoRepositorio.flush();
                servicoBiz.getMensagens().mensagem.add("CorridaClientePiloto atualizado com sucesso!");
            } else {
                servicoBiz.getMensagens().mensagem.add("Erro ao alterar!");
            }
        } catch (Exception e) {
            servicoBiz.getMensagens().mensagem.add("Erro ao alterar: " + e.getMessage());
        }
        return servicoBiz.getMensagens();
    }
}
