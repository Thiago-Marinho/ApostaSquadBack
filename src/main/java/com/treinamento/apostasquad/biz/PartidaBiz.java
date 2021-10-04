package com.treinamento.apostasquad.biz;

import com.treinamento.apostasquad.Mensagem;
import com.treinamento.apostasquad.entities.Partida;
import com.treinamento.apostasquad.repositories.EstadioRepository;

public class PartidaBiz {
	private Mensagem mensagem;
	
	private EstadioRepository estadioRepository;
	
	public PartidaBiz(EstadioRepository estadioRepository) {
		this.estadioRepository = estadioRepository;
		this.mensagem = new Mensagem();
	}

	public Mensagem getMensagem() {
		return mensagem;
	}

	public void setMensagem(Mensagem mensagem) {
		this.mensagem = mensagem;
	}
	
	public Boolean validarPartida(Partida partida) {
		Boolean validacao = true;
		
		if(partida.getData() == null) {
			mensagem.getMensagem().add("A data não deve estar em branco");
			validacao = false;
		}
		if(partida.getDescricao().isBlank()) {
			mensagem.getMensagem().add("A descricao da partida não deve ser vazio");
			validacao = false;
		}else if(partida.getDescricao().length() > 255) {
			mensagem.getMensagem().add("O nome do Estadio não deve ser maior que 255 caracteres");
			validacao = false;
		}
		if(partida.getId_estadio() == null) {
			mensagem.getMensagem().add("O estadio da partida não deve ser vazio");
			validacao = false;
		}
		if(estadioRepository.findById(partida.getId_estadio()).isEmpty()) {
			mensagem.getMensagem().add("Estádio não existe");
			validacao = false;
		}
		return validacao;
	}
}
