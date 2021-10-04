package com.treinamento.apostasquad.biz;

import com.treinamento.apostasquad.Mensagem;
import com.treinamento.apostasquad.entities.Time;
import com.treinamento.apostasquad.repositories.TimeRepository;

public class TimeBiz {
	
	private TimeRepository timeRepository;
	
	private Mensagem msg;
	
	public TimeBiz(TimeRepository timeRepository) {
		this.msg = new Mensagem();
		this.timeRepository = timeRepository;
	}
	
	public Boolean validar(Time time) {
		Boolean valido = true;
		
		if(time.getNome() == null || time.getNome().isEmpty()) {
			msg.mensagem.add("O nome nao pode estar vazio");
			valido = false;
		} else if (time.getNome().length() > 255) {
			msg.mensagem.add("Nao pode conter mais que 255 letras");
		}
		if(time.getNome().isBlank()) {
			msg.mensagem.add("O nome nao pode ser composto somente por espaço");
			valido = false;
		}
		return valido;
	}

	public TimeRepository getTimeRepository() {
		return timeRepository;
	}

	public void setTimeRepository(TimeRepository timeRepository) {
		this.timeRepository = timeRepository;
	}

	public Mensagem getMsg() {
		return msg;
	}

	public void setMsg(Mensagem msg) {
		this.msg = msg;
	}
	
}
