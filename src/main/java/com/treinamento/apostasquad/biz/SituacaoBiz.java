package com.treinamento.apostasquad.biz;

import com.treinamento.apostasquad.Mensagem;
import com.treinamento.apostasquad.entities.Situacao;
 
public class SituacaoBiz {
	private Mensagem mensagem;
	
	public SituacaoBiz() {
		this.mensagem = new Mensagem();
	}

	public Mensagem getMensagem() {
		return mensagem;
	}

	public void setMensagem(Mensagem mensagem) {
		this.mensagem = mensagem;
	}
	
	public Boolean validar(Situacao situacao) {
		Boolean validacao = true;
		
		if(situacao.getDescricao().isEmpty()) {
			mensagem.getMensagem().add("A descrição não pode estar vazia");
			validacao = false;
		}
		if(situacao.getDescricao().length() > 20) {
			mensagem.getMensagem().add("A descrição não deve ser maior que 20 caracteres");
			validacao = false;
		}
		if(situacao.getDescricao().isBlank()) {
			mensagem.getMensagem().add("A descricao nao pode ser composta por apenas espaço");
			validacao = false;
		}
		return validacao;
	}
}
