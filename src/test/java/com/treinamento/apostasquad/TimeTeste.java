package com.treinamento.apostasquad;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.treinamento.apostasquad.biz.TimeBiz;
import com.treinamento.apostasquad.controller.TimeController;
import com.treinamento.apostasquad.entities.Time;
import com.treinamento.apostasquad.repositories.TimeRepository;


@SpringBootTest
public class TimeTeste {

	@Autowired
	TimeRepository timeRepository;

	@Autowired
	TimeController controller;

	TimeBiz timeBiz = new TimeBiz();

	@Test
	public void TimeRepositoryTest() {

		Integer expected = 1;
		Integer result = (int) timeRepository.findById(1).get().getId();
		assertThat(expected).isEqualTo(result);

	}

	@Test
	public void TimeRepositoryInclirTest() {
		Integer expected = (int) this.timeRepository.count() + 1;

		Time novo = new Time();
		novo.setNome("Flamengo");
		timeRepository.save(novo);
		timeRepository.flush();
		Integer result = (int) this.timeRepository.count();
		assertThat(expected).isEqualTo(result);
	}

	@Test
	public void TimeControllerListarTest() {
		Integer expected = 0;
		Integer result = 0;

		expected = (int) timeRepository.count();
		result = controller.listarTime().size();
		assertThat(expected).isEqualTo(result);

	}

	@Test
	public void TimeControllerConsultarTest() {

		Time umTime = obterPrimeiroRegistro();
		Integer expected = umTime.getId();
		Time getTime = this.controller.consultar(expected);
		Integer result = getTime.getId();

		assertThat(result).isEqualTo(expected);
	}

	@Test
	public void TimeControllerAlterarTest() {

		Boolean expected = true;
		Boolean result = false;

		Time timeUpdate = obterPrimeiroRegistro();

		timeUpdate.setNome("Flamengo");

		Mensagem msg = this.controller.alterarTime(timeUpdate);

		if (msg.ContemErro()) {
			result = false;
		} else {
			Time time = controller.consultar(timeUpdate.getId());

			if (time.getId() == timeUpdate.getId()) {
				result = true;
			}
		}

		assertThat(result).isEqualTo(expected);
	}

	@Test
	public void TimeBizValidarTest() {

		TimeBiz timeBiz = new TimeBiz();

		Boolean result = true;
		Boolean expected = true;

		Time time = new Time();

		// esperamos receber falso!
		time.setNome("Lorem ipsum dolor sit amet, consectetur adipiscing elit. "
				+ "Etiam maximus commodo nulla, at vestibulum neque aliquam sed. "
				+ "Donec cursus, erat mollis dapibus egestas, metus nunc pretium nulla, "
				+ "pretium sodales dui ligula sed nunc. " + "Vivamus porta nisi vitae augue cursus pulvinar. "
				+ "Praesent vehicula vitae mauris non sollicitudin. " + "Vivamus condimentum imperdiet arcu, quis.");
		if (timeBiz.validar(time)) {
			result = false;
		}
		assertThat(result).isEqualTo(expected);
	}

	public Time obterPrimeiroRegistro() {
		Page<Time> pagina = timeRepository.findAll(PageRequest.of(0, 1, Sort.by(Sort.Direction.ASC, "id")));
		return pagina.toList().get(0);
	}
}
