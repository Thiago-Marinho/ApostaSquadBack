package com.treinamento.apostasquad.biz;

import com.treinamento.apostasquad.Mensagem;
import com.treinamento.apostasquad.entities.Estadio;

public class EstadioBiz {
	private Mensagem mensagem;
	
	public EstadioBiz() {
		this.mensagem = new Mensagem();
	}

	public Mensagem getMensagem() {
		return mensagem;
	}

	public void setMensagem(Mensagem mensagem) {
		this.mensagem = mensagem;
	}
	
	public Boolean validar(Estadio estadio) {
		Boolean validacao = true;
		
		if(estadio.getDescricao().isBlank()) {
			mensagem.getMensagem().add("O nome do Estadio não deve estar em branco");
			validacao = false;
		}
		if(estadio.getDescricao().length() > 255) {
			mensagem.getMensagem().add("O nome do Estadio não deve ser maior que 255 caracteres");
			validacao = false;
		}
		return validacao;
	}
	
}
