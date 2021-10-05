package com.treinamento.apostasquad;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.treinamento.apostasquad.controller.ClienteController;
import com.treinamento.apostasquad.entities.Cliente;
import com.treinamento.apostasquad.repositories.ClienteRepository;

@SpringBootTest
public class ClienteTest {
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private ClienteController clienteController;

	@Test
	public void ClienteListarTest() {
		Integer expected = 0;
		Integer result;
		expected = clienteRepository.findAll().size();
		result = clienteController.listarCliente().size();
		assertThat(result).isEqualTo(expected);
	}

	@Test
	public void ClienteConsultarTest() {
		Integer expected = getPrimeiroCliente().getId();
		Integer result = clienteController.consultar(expected).getId();
		assertThat(result).isEqualTo(expected);
	}

	@Test
	public void ClienteIncluirTest() {
		Integer expected = clienteRepository.findAll().size() + 1;
		Cliente cliente = new Cliente();
		cliente.setNome("Thor");
		clienteController.incluirCliente(cliente);
		Integer result = clienteController.listarCliente().size();
		assertThat(result).isEqualTo(expected);
	}

	@Test
	public void ClienteAlterarTest() {
		Boolean expected = true;
		Boolean result = false;

		Cliente clienteUpdate = getPrimeiroCliente();
		clienteUpdate.setNome("Rogério");

		Mensagem mensagem = clienteController.alterarCliente(clienteUpdate);

		if (mensagem.ContemErro()) {
			result = false;
		} else {
			Cliente cliente = clienteController.consultar(clienteUpdate.getId());
			if (cliente.getId() == clienteUpdate.getId() && cliente.getNome() == clienteUpdate.getNome()) {
				result = true;
			}
		}
		assertThat(result).isEqualTo(expected);
	}

	public Cliente getPrimeiroCliente() {
		return clienteRepository.findAll(PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC, "id"))).toList().get(0);
	}
}
