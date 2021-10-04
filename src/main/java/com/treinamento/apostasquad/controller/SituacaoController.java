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
import com.treinamento.apostasquad.biz.SituacaoBiz;
import com.treinamento.apostasquad.entities.Situacao;
import com.treinamento.apostasquad.repositories.SituacaoRepository;

@RestController
@RequestMapping("situacao")
public class SituacaoController {
	
	@Autowired
	private SituacaoRepository equipeRepository;
	
	@GetMapping("listar")
	public List<Situacao> listarSituacao(){
		return equipeRepository.findAll();
	}	
	
	@CrossOrigin
	@GetMapping("/{id}")
	public Situacao consultar(@PathVariable Integer id) {
    	return equipeRepository.findById(id).get();
    }
    
	
	@PostMapping("incluir")
	public Mensagem incluirSituacao(@Validated @RequestBody Situacao equipe) {
		SituacaoBiz equipeBiz = new SituacaoBiz();
		try {
			if (equipeBiz.validar(equipe)) {
				equipeRepository.save(equipe);
				equipeRepository.flush();
				equipeBiz.getMensagem().mensagem.add("Situacao adicionada");
			}
		} catch (Exception e) {
			equipeBiz.getMensagem().mensagem.add("Erro ao Incluir:" + e.getMessage());
		}
		return equipeBiz.getMensagem();
	}
	
	@PutMapping(path="alterar")
    public Mensagem alterarSituacao(@RequestBody @Validated Situacao equipe) {
        
    	SituacaoBiz equipeBiz = new SituacaoBiz();
        try{
            if(equipeBiz.validar(equipe)) {
            	equipeRepository.save(equipe);
                equipeRepository.flush();
                equipeBiz.getMensagem().mensagem.add("Situacao alterado com sucesso");
            }else {
            	equipeBiz.getMensagem().mensagem.add("Erro ao alterar");
            }
        	
        }catch(Exception e) {
            equipeBiz.getMensagem().mensagem.add("Erro ao alterar: " + e.getMessage());
        }
        return equipeBiz.getMensagem();
    }
}
