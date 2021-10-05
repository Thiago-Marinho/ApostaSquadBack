package com.treinamento.apostasquad;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.treinamento.apostasquad.biz.ApostaBiz;
import com.treinamento.apostasquad.controller.ApostaController;
import com.treinamento.apostasquad.entities.Aposta;
import com.treinamento.apostasquad.repositories.ApostaRepository;
import com.treinamento.apostasquad.repositories.ClienteRepository;
import com.treinamento.apostasquad.repositories.SituacaoRepository;

@SpringBootTest
public class ApostaTest {

    @Autowired
    ApostaRepository apostaRepository;
    
    @Autowired
    ApostaController apostaController;
    
    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    SituacaoRepository situacaoRepository;
    
    @Test
    public void apostaControllerListarTest() {
        Integer expected = 0;
        expected = (int) this.apostaRepository.count();
        Integer result = this.apostaController.listarAposta().size();
        assertThat(expected).isEqualTo(result);
    }

    @Test
    public void apostaControllerConsultarTest() {
        Aposta expected = this.obterPrimeiroRegistro();
        Aposta result = this.apostaController.consultar(expected.getId());
        assertThat(expected.getId()).isEqualTo(result.getId());
    }

    @Test
    public void apostaControllerInserirTest() {
        Integer expected = (int) this.apostaRepository.count()+1;
        Aposta novoAposta = new Aposta();
        novoAposta.setValor(80.00);
        novoAposta.setDescricao("Flamengo x Fluminence");
        novoAposta.setIdCliente(5);
        novoAposta.setIdSituacao(1);
        this.apostaController.incluir(novoAposta);
        Integer result = this.apostaController.listarAposta().size();
        assertThat(expected).isEqualTo(result);
    }

    @Test
    public void ApostaControllerAlterarTest() {

        Boolean expected = true;
        Boolean result = false;

        Aposta apostaUpdate = obterPrimeiroRegistro();

        apostaUpdate.setValor(25.00);
        apostaUpdate.setDescricao("Palmeiras x Corinthians");
        apostaUpdate.setIdCliente(3);
        apostaUpdate.setIdSituacao(2);
        Mensagem msg = this.apostaController.alterar(apostaUpdate);

        if (msg.ContemErro()){
            result = false;
        } else {
            Aposta aposta  =
                    apostaController.consultar(apostaUpdate.getId());

            if (  aposta.getId() == apostaUpdate.getId() &&
                    aposta.getIdCliente() == apostaUpdate.getIdCliente() &&
                    aposta.getDescricao().equals(apostaUpdate.getDescricao())
            ) {
                result = true;
            }
        }
        assertThat(result).isEqualTo(expected);

    }

    @Test
    public void apostaBizValidarTest() {
        ApostaBiz apostaBiz = new ApostaBiz(clienteRepository, situacaoRepository);
        Boolean result = true;
        Boolean expected = true;

        Aposta aposta = new Aposta();
       
        // esperamos receber falso!
        aposta.setIdCliente(1000);
        aposta.setDescricao("Testando");
        aposta.setValor(15.00);
        aposta.setIdSituacao(1);
        
        if(apostaBiz.validarAposta(aposta)) result = false;

        // esperamos receber falso!
        aposta.setIdCliente(1);
        aposta.setDescricao("");
        aposta.setValor(15.00);
        aposta.setIdSituacao(1);
        if(apostaBiz.validarAposta(aposta)) result = false;

        // esperamos receber falso!
        aposta.setValor(101.00);
        if(apostaBiz.validarAposta(aposta)) result = false;

        //esperamos receber trues!
        aposta.setValor(9.00);
        if(apostaBiz.validarAposta(aposta)) result = true;

        assertThat(result).isEqualTo(expected);
    }

    public Aposta obterPrimeiroRegistro() {
        Page<Aposta> pagina = apostaRepository.findAll(
                PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC, "id")));
        return pagina.toList().get(0);
    }
}
