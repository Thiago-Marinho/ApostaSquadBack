package com.treinamento.apostasquad;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.treinamento.apostasquad.biz.SituacaoBiz;
import com.treinamento.apostasquad.controller.SituacaoController;
import com.treinamento.apostasquad.entities.Situacao;
import com.treinamento.apostasquad.repositories.SituacaoRepository;

@SpringBootTest
public class SituacaoTest {

    @Autowired
    SituacaoRepository situacaoRepository;
    
    @Autowired
    SituacaoController situacaoController;
    
    @Test
    public void situacaoControllerListarTest() {
        Integer expected = 0;
        expected = (int) this.situacaoRepository.count();
        Integer result = this.situacaoController.listarSituacao().size();
        assertThat(expected).isEqualTo(result);
    }

    @Test
    public void situacaoControllerConsultarTest() {
        Situacao expected = this.obterPrimeiroRegistro();
        Situacao result = this.situacaoController.consultar(expected.getId());
        assertThat(expected.getId()).isEqualTo(result.getId());
    }

    @Test
    public void situacaoControllerInserirTest() {
        Integer expected = (int) this.situacaoRepository.count()+1;
        Situacao novoSituacao = new Situacao();
        novoSituacao.setDescricao("Acertou");
        this.situacaoController.incluirSituacao(novoSituacao);
        Integer result = this.situacaoController.listarSituacao().size();
        assertThat(expected).isEqualTo(result);
    }

    @Test
    public void situacaoControllerAlterarTest() {

        Boolean expected = true;
        Boolean result = false;

        Situacao situacaoUpdate = obterPrimeiroRegistro();

        situacaoUpdate.setDescricao("Errou");
        Mensagem msg = this.situacaoController.alterarSituacao(situacaoUpdate);

        if (msg.ContemErro()){
            result = false;
        } else {
            Situacao situacao  =
                    situacaoController.consultar(situacaoUpdate.getId());

            if (  situacao.getId() == situacaoUpdate.getId()) {
                result = true;
            }
        }
        assertThat(result).isEqualTo(expected);

    }

    @Test
    public void situacaoBizValidarTest() {
        SituacaoBiz situacaoBiz = new SituacaoBiz();
        Boolean result = true;
        Boolean expected = true;

        Situacao situacao = new Situacao();
       
        // esperamos receber falso!
        situacao.setDescricao("Testando o tamanho da string de descricao, sendo menor que 20 caracteres");
        if(situacaoBiz.validar(situacao)) result = false;

        //esperamos receber trues!
        situacao.setDescricao("");
        if(situacaoBiz.validar(situacao)) result = true;

        assertThat(result).isEqualTo(expected);

    }

    public Situacao obterPrimeiroRegistro() {
        Page<Situacao> pagina = situacaoRepository.findAll(
                PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC, "id")));
        return pagina.toList().get(0);
    }
}
