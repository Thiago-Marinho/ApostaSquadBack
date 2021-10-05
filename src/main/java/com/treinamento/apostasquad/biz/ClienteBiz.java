package com.treinamento.apostasquad.biz;

import com.treinamento.apostasquad.Mensagem;
import com.treinamento.apostasquad.entities.Cliente;
import com.treinamento.apostasquad.repositories.ClienteRepository;

public class ClienteBiz {
	
	private ClienteRepository clienteRepository;
	
	private Mensagem msg;
	
	public ClienteBiz(ClienteRepository clienteRepository) {
		this.msg = new Mensagem();
		this.clienteRepository = clienteRepository;
	}
	
	public Boolean validar(Cliente cliente) {
		Boolean valido = true;
		
		if(cliente.getNome() == null || cliente.getNome().isEmpty()) {
			msg.mensagem.add("O nome nao pode estar vazio");
			valido = false;
		} else if (cliente.getNome().length() > 255) {
			msg.mensagem.add("Nao pode conter mais que 255 letras");
			valido = false;
		}
		if(cliente.getNome().isBlank()) {
			msg.mensagem.add("O nome nao pode ser composto somente por espaço");
			valido = false;
		}
		return valido;
	}

	public ClienteRepository getClienteRepository() {
		return clienteRepository;
	}

	public void setClienteRepository(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}

	public Mensagem getMsg() {
		return msg;
	}

	public void setMsg(Mensagem msg) {
		this.msg = msg;
	}
	
}
