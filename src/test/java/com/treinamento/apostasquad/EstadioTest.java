package com.treinamento.apostasquad;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.treinamento.apostasquad.biz.EstadioBiz;
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
	public void EstadioListarTest() {
		Integer expected = 0;
		Integer result;
		expected = estadioRepository.findAll().size();
		result = estadioController.listarEstadios().size();
		assertThat(result).isEqualTo(expected);
	}
	@Test
	public void EstadioConsultarTest() {
		Integer expected = getPrimeiroEstadio().getId();
		Integer result = estadioController.listarEstadio(expected).getId();
		assertThat(result).isEqualTo(expected);
	}
	
	@Test
	public void EstadioIncluirTest() {
		Integer expected = estadioRepository.findAll().size() + 1;
		Estadio estadio = new Estadio();
		estadio.setDescricao("Morenão");
		estadioController.incluirEstadio(estadio);
		Integer result = estadioController.listarEstadios().size();
		assertThat(result).isEqualTo(expected);
	}
	
	@Test
	public void EstadioAlterarTest() {
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
    				&& estadio.getDescricao().equals(estadioUpdate.getDescricao())) {
    			result = true;
    		}
    	}
    	assertThat(result).isEqualTo(expected);
	}
	
	public void EstadioBizTest() {
		EstadioBiz estadioBiz = new EstadioBiz();
		Estadio estadio = new Estadio();
		Boolean expected = true;
		Boolean result = true;
		
		estadio.setDescricao(
				"Lorem ipsum dolor sit amet, consectetur adipiscing elit. "
				+ "Etiam maximus commodo nulla, at vestibulum neque aliquam sed. "
				+ "Donec cursus, erat mollis dapibus egestas, metus nunc pretium nulla, "
				+ "pretium sodales dui ligula sed nunc. " + "Vivamus porta nisi vitae augue cursus pulvinar. "
				+ "Praesent vehicula vitae mauris non sollicitudin. " 
				+ "Vivamus condimentum imperdiet arcu, quis."
				);
		if(estadioBiz.validar(estadio)) {
			result = false;
		}
		assertThat(result).isEqualTo(expected);
	}
	
	public Estadio getPrimeiroEstadio(){
        return estadioRepository.findAll(
                PageRequest.of(0, 1, Sort.by(Sort.Direction.ASC, "id"))).toList().get(0);
    }
}
