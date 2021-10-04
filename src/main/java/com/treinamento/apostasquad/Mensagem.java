package com.treinamento.apostasquad;

import java.util.ArrayList;
import java.util.List;

public class Mensagem {
	
	public List<String> mensagem;

    public Mensagem() {
        this.mensagem = new ArrayList<>();
    }

    public List<String> getMensagem() {
        return mensagem;
    }

    public void setMensagem(List<String> mensagem) {
        this.mensagem = mensagem;
    }

    public Boolean ContemErro() {
    	for(String m : this.mensagem) {
    		if (m.contains("Erro")) {
    			return true;
    		}
    	}
    	return false;
    }
}
