package br.com.aeviagens.backend.endpoints.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DadosParticipanteDTO {
    private String nome;
    private String cpf;
    private String endereco;
}
