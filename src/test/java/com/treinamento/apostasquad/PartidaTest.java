package com.treinamento.apostasquad;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.treinamento.apostasquad.biz.PartidaBiz;
import com.treinamento.apostasquad.controller.PartidaController;
import com.treinamento.apostasquad.entities.Partida;
import com.treinamento.apostasquad.repositories.EstadioRepository;
import com.treinamento.apostasquad.repositories.PartidaRepository;

@SpringBootTest
public class PartidaTest {
	

    
	@Autowired
    PartidaRepository partidaRepository;
    
    @Autowired
    PartidaController partidaController;
    
    @Autowired
    EstadioRepository estadioRepository;

    @Test
    public void partidaControllerListarTest() {
        Integer expected = 0;
        expected = (int) this.partidaRepository.count();
        Integer result = this.partidaController.listarPartidas().size();
        assertThat(expected).isEqualTo(result);
    }

    @Test
    public void partidaControllerConsultarTest() {
        Partida expected = this.obterPrimeiroRegistro();
        Partida result = this.partidaController.listarPartida(expected.getId());
        assertThat(expected.getId()).isEqualTo(result.getId());
    }

    @SuppressWarnings("deprecation")
	@Test
    public void partidaControllerInserirTest() {

    	Date data = new Date("20/12/2020");
        	
    	Integer expected = (int) this.partidaRepository.count()+1;
        Partida novoPartida = new Partida();
        novoPartida.setData(data);
        novoPartida.setDescricao("Partida 4");
        novoPartida.setId_estadio(3);
        this.partidaController.incluirPartida(novoPartida);
        Integer result = this.partidaController.listarPartidas().size();
        assertThat(expected).isEqualTo(result);
    }

    @Test
    public void PartidaControllerAlterarTest() {

        Boolean expected = true;
        Boolean result = false;

        Partida partidaUpdate = obterPrimeiroRegistro();

        String date = "13/11/1997";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date dataNova = null;
		try {
			dataNova = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
        
        partidaUpdate.setId(1);
        partidaUpdate.setData(dataNova);
        partidaUpdate.setDescricao("Partida 171");
        partidaUpdate.setId_estadio(3);
        
        Mensagem msg = this.partidaController.alterarPartida(partidaUpdate);

        if (msg.ContemErro()){
            result = false;
        } else {
            Partida partida  =
                    partidaController.listarPartida(partidaUpdate.getId());

            if (  partida.getId() == partidaUpdate.getId() &&
                    partida.getId_estadio() == partidaUpdate.getId_estadio() &&
                    partida.getDescricao().equals(partidaUpdate.getDescricao())
            ) {
                result = true;
            }
        }
        assertThat(result).isEqualTo(expected);

    }

	@Test
    public void partidaBizValidarTest() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		PartidaBiz partidaBiz = new PartidaBiz(estadioRepository);
		
        Boolean result = true;
        Boolean expected = true;

        Partida partida = new Partida();
       
        // esperamos receber falso!
        
        partida.setData(null);
        partida.setDescricao("Testando");
        partida.setId_estadio(1);
        
        if(partidaBiz.validarPartida(partida)) result = false;

        // esperamos receber falso!
        partida.setData(null);
        partida.setDescricao("");
        partida.setId_estadio(99999999);
        if(partidaBiz.validarPartida(partida)) result = false;

        assertThat(result).isEqualTo(expected);
    }

    public Partida obterPrimeiroRegistro() {
        Page<Partida> pagina = partidaRepository.findAll(
                PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC, "id")));
        return pagina.toList().get(0);
    }
}
