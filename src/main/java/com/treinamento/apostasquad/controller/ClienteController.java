package com.treinamento.apostasquad.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.treinamento.apostasquad.Mensagem;
import com.treinamento.apostasquad.biz.ClienteBiz;
import com.treinamento.apostasquad.entities.Cliente;
import com.treinamento.apostasquad.repositories.ClienteRepository;

@RestController
@RequestMapping("cliente")
@CrossOrigin
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@GetMapping("listar")
	public List<Cliente> listarCliente(){
		List<Cliente> lista = clienteRepository.findAll();
		return lista;
	}
	
	@CrossOrigin
	@GetMapping("/{id}")
    public Cliente consultar(@PathVariable Integer id) {
        return clienteRepository.findById(id).get();
    }
	
	@PostMapping("incluir")
	public Mensagem incluirCliente(@Validated @RequestBody Cliente cliente) {
		
		ClienteBiz clienteBiz = new ClienteBiz(this.clienteRepository);
		try {
			
			if (clienteBiz.validar(cliente)) {
				clienteRepository.save(cliente);
				clienteRepository.flush();
				clienteBiz.getMsg().getMensagem().add("OK");
			}
			
		} catch (Exception e) {
			clienteBiz.getMsg().getMensagem().add("Erro ao Incluir:" + e.getMessage());
		}
		return clienteBiz.getMsg();
	}
	
	@PutMapping("alterar/{id}")
    public String alterarCliente(@RequestBody @Validated Cliente cliente) {

        try {
            clienteRepository.save(cliente);
            clienteRepository.flush();
            return " Alterado com sucesso";
        } catch (Exception e) {
            return e.getMessage();
	}

	}
	

}
