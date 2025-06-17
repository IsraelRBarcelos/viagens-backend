package br.com.aeviagens.backend.fixtures;

import br.com.aeviagens.backend.domain.DadosDeLocalizacao;

public class DadosDeLocalizacaoFixture implements Fixture<DadosDeLocalizacao>{
    @Override
    public DadosDeLocalizacao recuperarDados() {
        return DadosDeLocalizacao.builder()
                .cep(12342312L)
                .numero(123)
                .rua("rua")
                .bairro("bairro")
                .pais("pais")
                .cidade("cidade")
                .build();
    }
}
