package com.treinamento.apostasquad.biz;

import com.treinamento.apostasquad.Mensagem;
import com.treinamento.apostasquad.entities.Aposta;
import com.treinamento.apostasquad.repositories.ClienteRepository;
import com.treinamento.apostasquad.repositories.SituacaoRepository;

public class ApostaBiz {
	
	private Mensagem mensagens = new Mensagem();

    private ClienteRepository clienteRepository;
    
    private SituacaoRepository situacaoRepository;

    public ApostaBiz(ClienteRepository clienteRepository, SituacaoRepository situacaoRepository){
        this.clienteRepository = clienteRepository;
        this.situacaoRepository = situacaoRepository;
    }
    
    public Mensagem getMensagens() {
        return mensagens;
    }
    
    public void setMensagens(Mensagem mensagens) {
        this.mensagens = mensagens;
    }

    public boolean validarAposta(Aposta aposta){
        
    	boolean valido = true;
        
        if(aposta.getValor() < 10 || aposta.getValor() > 100){
            this.mensagens.mensagem.add("Valor informado fora do limite, deve ser de R$ 10,00 até R$ 100,00");
            valido=false;
        }
        
        if(aposta.getDescricao().isBlank()){
            this.mensagens.mensagem.add("A descrição inserida não deve ser nula");
            valido=false;
        }else if(aposta.getDescricao().length()>255){
            this.mensagens.mensagem.add("A descrição inserida não deve possuir mais que 255 caracteres");
            valido=false;
        }
        
        if(aposta.getIdCliente()==null){
            this.mensagens.mensagem.add("O Id do cliente inserido não pode ser nulo");
            valido=false;
        }else if(clienteRepository.findById(aposta.getIdCliente()).isEmpty()){
            this.mensagens.mensagem.add("Nenhum cliente foi encontrado com o Id informado");
            valido=false;
        }
        
        if(aposta.getIdSituacao()==null){
            this.mensagens.mensagem.add("O Id da situação inserida não pode ser nulo");
            valido=false;
        }else if(situacaoRepository.findById(aposta.getIdSituacao()).isEmpty()){
            this.mensagens.mensagem.add("Nenhuma situação foi encontrada com o Id informado");
            valido=false;
        }
        
        if(!valido){
            this.mensagens.mensagem.add("A aposta informada não é válida!");
        }
        
        return valido;
    }
}
