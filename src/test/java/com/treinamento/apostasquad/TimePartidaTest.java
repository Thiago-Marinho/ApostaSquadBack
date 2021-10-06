package com.treinamento.apostasquad;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.treinamento.apostasquad.biz.TimePartidaBiz;
import com.treinamento.apostasquad.controller.TimePartidaController;
import com.treinamento.apostasquad.entities.TimePartida;
import com.treinamento.apostasquad.repositories.PartidaRepository;
import com.treinamento.apostasquad.repositories.TimePartidaRepository;
import com.treinamento.apostasquad.repositories.TimeRepository;




@SpringBootTest
public class TimePartidaTest {
	
	@Autowired
    TimePartidaController controller;
    @Autowired
    TimePartidaRepository timePartidaRepository;
    @Autowired
    TimeRepository timeRepository;
    @Autowired
    PartidaRepository partidaRepository;

    @Test
	public void TimePartidaRepositoryTest() {

		Integer expected = 1;
		Integer result = (int) timePartidaRepository.findById(1).get().getId();
		assertThat(expected).isEqualTo(result);

	}
    
    @Test
	public void TimePartidaRepositoryInclirTest() {
		Integer expected = (int) this.timePartidaRepository.count() + 1;
		
		TimePartida novo = new TimePartida();
		novo.setResultado(false);
		novo.setIdTime(timeRepository.findById(1).get().getId());
		novo.setIdPartida(partidaRepository.findById(1).get().getId());
		timePartidaRepository.save(novo);
		timePartidaRepository.flush();
		Integer result = (int) this.timePartidaRepository.count();
		assertThat(expected).isEqualTo(result);
	}
    
    @Test
	public void TimePatidaControllerListarTest() {
		Integer expected = 0;
		Integer result = 0;

		expected = (int) timePartidaRepository.count();
		result = controller.listarTimePartida().size();
		assertThat(expected).isEqualTo(result);
	}
    
    @Test
	public void TipoPartidaControllerConsultarTest() {
    	
        TimePartida umTimePartida = obterPrimeiroRegistro();
        Integer expected = umTimePartida.getId();
        TimePartida getTimePartida = this.controller.consultar(expected);
        Integer result = getTimePartida.getId();

		assertThat(result).isEqualTo(expected);
	}
    
    @Test
	public void TimeControllerAlterarTest() {

		Boolean expected = true;
		Boolean result = false;

		TimePartida timePartidaUpdate = obterPrimeiroRegistro();

		timePartidaUpdate.setIdPartida(1);
		timePartidaUpdate.setIdTime(1);

		Mensagem msg = this.controller.alterar(timePartidaUpdate);

		if (msg.ContemErro()) {
			result = false;
		} else {
			TimePartida timePartida = controller.consultar(timePartidaUpdate.getId());

			if (timePartida.getId() == timePartidaUpdate.getId()) {
				result = true;
			}
		}

		assertThat(result).isEqualTo(expected);
	}
    
    @Test
	public void TimePartidaBizValidarTest() {
    	
		TimePartidaBiz timePartidaBiz = new TimePartidaBiz(partidaRepository, timeRepository); 
		
        Boolean result = true;
        Boolean expected = true;

        TimePartida timePartida = new TimePartida();
        timePartida.setIdTime(1);
        timePartida.setIdPartida(2);
        timePartida.setResultado(true);
		boolean teste = timePartidaBiz.validarTimePartida(timePartida);
		if(!teste){
			result=false;
		} 
	
        assertThat(result).isEqualTo(expected);                                           
    }

    public TimePartida obterPrimeiroRegistro() {
    	Page<TimePartida> pagina = timePartidaRepository.findAll(
    	    	PageRequest.of(0, 1, Sort.by(Sort.Direction.ASC, "id")));
    	return pagina.toList().get(0);
    }
    
}
