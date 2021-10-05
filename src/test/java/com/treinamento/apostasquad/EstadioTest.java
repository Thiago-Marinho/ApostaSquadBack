package com.treinamento.apostasquad;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.treinamento.apostasquad.controller.EstadioController;
import com.treinamento.apostasquad.entities.Estadio;
import com.treinamento.apostasquad.repositories.EstadioRepository;

@SpringBootTest
public class EstadioTest {
	@Autowired
	EstadioRepository estadioRepository;
	@Autowired
	EstadioController estadioController;
	
	@Test
	public void ClienteListarTest() {
		Integer expected = 0;
		Integer result;
		expected = estadioRepository.findAll().size();
		result = estadioController.listarEstadios().size();
		assertThat(result).isEqualTo(expected);
	}
	@Test
	public void ClienteConsultarTest() {
		Integer expected = getPrimeiroEstadio().getId();
		Integer result = estadioController.listarEstadio(expected).getId();
		assertThat(result).isEqualTo(expected);
	}
	
	@Test
	public void ClienteIncluirTest() {
		Integer expected = estadioRepository.findAll().size() + 1;
		Estadio estadio = new Estadio();
		estadio.setDescricao("Morenão");
		estadioController.incluirEstadio(estadio);
		Integer result = estadioController.listarEstadios().size();
		assertThat(result).isEqualTo(expected);
	}
	
	@Test
	public void ClienteAlterarTest() {
		Boolean expected = true;
    	Boolean result = false;
    	
    	Estadio estadioUpdate = getPrimeiroEstadio();
    	estadioUpdate.setDescricao("Rogério");
    	
    	Mensagem mensagem = estadioController.alterarEstadio(estadioUpdate);
    	
    	if(mensagem.ContemErro()) {
    		result = false;
    	}else {
    		Estadio estadio = estadioController.listarEstadio(estadioUpdate.getId());
    		if(estadio.getId() == estadioUpdate.getId()
    				&& estadio.getDescricao() == estadioUpdate.getDescricao()) {
    			result = true;
    		}
    	}
    	assertThat(result).isEqualTo(expected);
	}
	
	public Estadio getPrimeiroEstadio(){
        return estadioRepository.findAll(
                PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC, "id"))).toList().get(0);
    }
}
