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

    public boolean validarAposta(Aposta aposta){
        boolean valido = true;
        if(aposta.getDescricao().isBlank()){
            this.mensagens.mensagem.add("A descrição inserida não deve ser nula");
            valido=false;
        }else if(aposta.getDescricao().length()>255){
            this.mensagens.mensagem.add("A descrição inserida não deve possuir mais que 255 caracteres");
            valido=false;
        }
        if(aposta.getId()==null){
            this.mensagens.mensagem.add("O Id do cliente inserido não deve ser nulo");
            valido=false;
        }else if(clienteRepository.findById(aposta.getIdCliente()).isEmpty()){
            this.mensagens.mensagem.add("Nenhum cliente foi encontrado com o Id informado");
            valido=false;
        }
        if(!valido){
            this.mensagens.mensagem.add("O aposta informado não é válido!");
        }
        return valido;
    }

    public Mensagem getMensagens() {
        return mensagens;
    }
    public void setMensagens(Mensagem mensagens) {
        this.mensagens = mensagens;
    }
}
