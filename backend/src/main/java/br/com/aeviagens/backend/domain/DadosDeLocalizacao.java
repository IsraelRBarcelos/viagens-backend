package br.com.aeviagens.backend.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DadosDeLocalizacao {
    private int numero;
    private String rua;
    private String bairro;
    private String cidade;
    private String estado;
    private String pais;
    private Long cep;
}
