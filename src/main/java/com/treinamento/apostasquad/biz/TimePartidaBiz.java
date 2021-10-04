package com.treinamento.apostasquad.biz;

import com.treinamento.apostasquad.Mensagem;
import com.treinamento.apostasquad.entities.TimePartida;
import com.treinamento.apostasquad.repositories.PartidaRepository;
import com.treinamento.apostasquad.repositories.TimeRepository;

public class TimePartidaBiz {
	
	private Mensagem mensagens = new Mensagem();

    private PartidaRepository partidaRepository;
    
    private TimeRepository timeRepository;

    public TimePartidaBiz(PartidaRepository partidaRepository, TimeRepository timeRepository){
        this.partidaRepository = partidaRepository;
        this.timeRepository = timeRepository;
    }
    
    public Mensagem getMensagens() {
        return mensagens;
    }
    
    public void setMensagens(Mensagem mensagens) {
        this.mensagens = mensagens;
    }

    public boolean validarTimePartida(TimePartida timePartida){
        
    	boolean valido = true;
        
        if(timePartida.getIdPartida()==null){
            this.mensagens.mensagem.add("O Id do partida inserido não pode ser nulo");
            valido=false;
        }else if(partidaRepository.findById(timePartida.getIdPartida()).isEmpty()){
            this.mensagens.mensagem.add("Nenhum partida foi encontrado com o Id informado");
            valido=false;
        }
        
        if(timePartida.getIdTime()==null){
            this.mensagens.mensagem.add("O Id da situação inserida não pode ser nulo");
            valido=false;
        }else if(timeRepository.findById(timePartida.getIdTime()).isEmpty()){
            this.mensagens.mensagem.add("Nenhuma situação foi encontrada com o Id informado");
            valido=false;
        }
        
        if(!valido){
            this.mensagens.mensagem.add("A timePartida informada não é válida!");
        }
        
        return valido;
    }
}