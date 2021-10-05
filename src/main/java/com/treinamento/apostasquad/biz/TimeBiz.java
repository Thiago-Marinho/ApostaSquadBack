package com.treinamento.apostasquad.biz;

import com.treinamento.apostasquad.Mensagem;
import com.treinamento.apostasquad.entities.Time;
import com.treinamento.apostasquad.repositories.TimeRepository;

public class TimeBiz {
	
	
	private Mensagem msg;
	
	public TimeBiz() {
		this.msg = new Mensagem();
		
	}
	
	public Boolean validar(Time time) {
		Boolean valido = true;
		
		if(time.getNome() == null || time.getNome().isEmpty()) {
			msg.mensagem.add("O nome nao pode estar vazio");
			valido = false;
		} else if (time.getNome().length() > 255) {
			msg.mensagem.add("Nao pode conter mais que 255 letras");
			valido = false;
		}
		if(time.getNome().isBlank()) {
			msg.mensagem.add("O nome nao pode ser composto somente por espaço");
			valido = false;
		}
		return valido;
	}

	public Mensagem getMsg() {
		return msg;
	}

	public void setMsg(Mensagem msg) {
		this.msg = msg;
	}
	
}
