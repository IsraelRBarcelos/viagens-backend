package br.com.aeviagens.backend.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DadosDoCartao {
    private Long numeroDoCartao;
    private String dataValidade;
    private int cvv;
    private String nomeTitular;
    private Bandeira bandeira;
}
