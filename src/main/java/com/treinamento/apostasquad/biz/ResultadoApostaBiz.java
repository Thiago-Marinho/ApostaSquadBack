package com.treinamento.apostasquad.biz;

import com.treinamento.apostasquad.Mensagem;
import com.treinamento.apostasquad.entities.ResultadoAposta;
import com.treinamento.apostasquad.repositories.ApostaRepository;
import com.treinamento.apostasquad.repositories.TimePartidaRepository;

public class ResultadoApostaBiz {
	private Mensagem mensagem;
	
	private ApostaRepository apostaRepository;
	
	private TimePartidaRepository timePartidaRepository;
	
	public ResultadoApostaBiz(ApostaRepository apostaRepository, TimePartidaRepository timePartidaRepository) {
		this.apostaRepository = apostaRepository;
		this.timePartidaRepository = timePartidaRepository;
		this.mensagem = new Mensagem();
	}

	public Mensagem getMensagem() {
		return mensagem;
	}

	public void setMensagem(Mensagem mensagem) {
		this.mensagem = mensagem;
	}
	
	public Boolean validarResultadoAposta(ResultadoAposta resultadoAposta) {
		Boolean validacao = true;
		
		if(resultadoAposta.getStatus_time() == null) {
			mensagem.getMensagem().add("O status do time não deve ser vazia");
			validacao = false;
		}
	
		if(resultadoAposta.getId_aposta() == null) {
			mensagem.getMensagem().add("A aposta não deve ser vazia");
			validacao = false;
		}else if(apostaRepository.findById(resultadoAposta.getId_aposta()).isEmpty()) {
			mensagem.getMensagem().add("A aposta não existe");
			validacao = false;
		}
		
		if(resultadoAposta.getId_time_partida() == null) {
			mensagem.getMensagem().add("O time partida não deve ser vazia");
			validacao = false;
		}else if(timePartidaRepository.findById(resultadoAposta.getId_time_partida()).isEmpty()) {
			mensagem.getMensagem().add("O time partida não existe");
			validacao = false;
		}
		return validacao;
	}
	
}
