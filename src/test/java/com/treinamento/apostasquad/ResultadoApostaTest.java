package com.treinamento.apostasquad;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.treinamento.apostasquad.biz.ResultadoApostaBiz;
import com.treinamento.apostasquad.controller.ResultadoApostaController;
import com.treinamento.apostasquad.entities.ResultadoAposta;
import com.treinamento.apostasquad.repositories.ApostaRepository;
import com.treinamento.apostasquad.repositories.ResultadoApostaRepository;
import com.treinamento.apostasquad.repositories.TimePartidaRepository;

@SpringBootTest
public class ResultadoApostaTest {
	
	@Autowired
	ResultadoApostaRepository resultadoApostaRepository;
	
	@Autowired
	ResultadoApostaController resultadoApostaController;
	
	@Autowired
	ApostaRepository apostaRepository;
	
	@Autowired
	TimePartidaRepository timePartidaRepository;
	
		@Test
		public void resultadoApostaControllerListarTest() {
			Integer expected = 0;
			expected = (int) this.resultadoApostaRepository.count();
			Integer result = this.resultadoApostaController.listarResultadoApostas().size();
			assertThat(expected).isEqualTo(result);
		}
	
		@Test
		public void resultadoApostaControllerConsultaTest() {
			ResultadoAposta expected = this.obterPrimeiroRegistro();
			ResultadoAposta result = this.resultadoApostaController.listarResultadoAposta(expected.getId());
			assertThat(expected.getId()).isEqualTo(result.getId());
		}
		@Test
		public void resultadoApostaControllerInserirTest() {
			Integer expected = (int) this.resultadoApostaRepository.count()+1;
			ResultadoAposta novoResultadoAposta = new ResultadoAposta();
			novoResultadoAposta.setStatus_time(false);
			novoResultadoAposta.setId_aposta(1);
			novoResultadoAposta.setId_time_partida(1);
			this.resultadoApostaController.incluirResultadoAposta(novoResultadoAposta);
			Integer result = this.resultadoApostaController.listarResultadoApostas().size();
			assertThat(expected).isEqualTo(result);			
		}
		@Test
		public void ResultadoApostaControllerAlterarTest() {
			Boolean expected = true;
			Boolean result = false;
			
			ResultadoAposta resultadoApostaUpdate = obterPrimeiroRegistro();
			resultadoApostaUpdate.setStatus_time(false);
			resultadoApostaUpdate.setId_aposta(1);
			resultadoApostaUpdate.setId_time_partida(1);
			Mensagem msg = this.resultadoApostaController.alterarResultadoAposta(resultadoApostaUpdate);
			
			if(msg.ContemErro()) {
				result = false;
			} else {
				ResultadoAposta resultadoAposta = 
						resultadoApostaController.listarResultadoAposta(resultadoApostaUpdate.getId());
			if(resultadoAposta.getId() == resultadoApostaUpdate.getId() && 
					resultadoAposta.getStatus_time() == resultadoApostaUpdate.getStatus_time() &&
					resultadoAposta.getId_aposta() == resultadoApostaUpdate.getId_aposta() && 
					resultadoAposta.getId_time_partida() == resultadoApostaUpdate.getId_time_partida()) 
			{
				result = true;
			}
			
			}
			 assertThat(result).isEqualTo(expected);
		}
		
		@Test
		public void resultadoApostaBizValidarTest() {
			ResultadoApostaBiz resultadoApostaBiz = 
					new ResultadoApostaBiz(apostaRepository, timePartidaRepository);
			Boolean result = true;
			Boolean expected = true;
			
			ResultadoAposta resultadoAposta = new ResultadoAposta();
			
			resultadoAposta.setStatus_time(false);
			resultadoAposta.setId_aposta(1);
			resultadoAposta.setId_time_partida(1);
			
			if(resultadoApostaBiz.validarResultadoAposta(resultadoAposta)) result = false;
			
			resultadoAposta.setStatus_time(true);
			resultadoAposta.setId_aposta(1);
			resultadoAposta.setId_time_partida(1);
			
			if(resultadoApostaBiz.validarResultadoAposta(resultadoAposta)) result = false;
			
			resultadoAposta.setStatus_time(true);
			
			if(resultadoApostaBiz.validarResultadoAposta(resultadoAposta)) result = false;
			
			resultadoAposta.setStatus_time(false);
			
			if(resultadoApostaBiz.validarResultadoAposta(resultadoAposta)) result = true;
			
			assertThat(result).isEqualTo(expected);
		}
				
		public ResultadoAposta obterPrimeiroRegistro() {
	        Page<ResultadoAposta> pagina = resultadoApostaRepository.findAll(
	                PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC, "id")));
	        return pagina.toList().get(0);
	    }
	  
	  
	

}
