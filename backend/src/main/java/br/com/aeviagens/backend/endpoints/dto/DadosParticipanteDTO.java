package br.com.aeviagens.backend.endpoints.dto;

import br.com.aeviagens.backend.domain.DadosDeLocalizacao;
import br.com.aeviagens.backend.domain.DadosDoCartao;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DadosParticipanteDTO {
    private String nome;
    private String cpf;
    private String endereco;
    private DadosDoCartao dadosDoCartao;
    private DadosDeLocalizacao dadosDeLocalizacao;
}
