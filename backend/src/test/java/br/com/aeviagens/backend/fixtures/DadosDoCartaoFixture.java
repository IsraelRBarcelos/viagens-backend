package br.com.aeviagens.backend.fixtures;

import br.com.aeviagens.backend.domain.Bandeira;
import br.com.aeviagens.backend.domain.DadosDoCartao;

public class DadosDoCartaoFixture implements Fixture<DadosDoCartao> {

    @Override
    public DadosDoCartao recuperarDados() {
        return DadosDoCartao.builder()
                .cvv(123)
                .bandeira(Bandeira.ELO)
                .dataValidade("232025")
                .nomeTitular("nome")
                .numeroDoCartao(12321321L)
                .build();
    }
}
